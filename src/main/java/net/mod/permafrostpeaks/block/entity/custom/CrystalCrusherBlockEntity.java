package net.mod.permafrostpeaks.block.entity.custom;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.mod.permafrostpeaks.block.entity.ImplementedInventory;
import net.mod.permafrostpeaks.block.entity.ModBlockEntities;
import net.mod.permafrostpeaks.item.ModItems;
import net.mod.permafrostpeaks.screen.custom.CrystalCrusherScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrystalCrusherBlockEntity extends BlockEntity
        implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    private record CrusherRecipe(Item input, Item output, int count) {}

    private static final List<CrusherRecipe> RECIPES = List.of(
            new CrusherRecipe(ModItems.GREEN_CRYSTAL_SHARD, ModItems.GREEN_CRYSTAL_DUST, 1),
            new CrusherRecipe(ModItems.BLUE_CRYSTAL_SHARD, ModItems.BLUE_CRYSTAL_DUST, 1),
            new CrusherRecipe(ModItems.RED_CRYSTAL_SHARD, ModItems.RED_CRYSTAL_DUST, 1),
            new CrusherRecipe(ModItems.YELLOW_CRYSTAL_SHARD, ModItems.YELLOW_CRYSTAL_DUST, 1)
    );

    private int progress = 0;
    private int maxProgress = 72;

    private int burnTime = 0;
    private int maxBurnTime = 0;

    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> progress;
                case 1 -> maxProgress;
                case 2 -> burnTime;
                case 3 -> maxBurnTime;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> progress = value;
                case 1 -> maxProgress = value;
                case 2 -> burnTime = value;
                case 3 -> maxBurnTime = value;
            }
        }

        @Override
        public int size() {
            return 4;
        }
    };

    public CrystalCrusherBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRYSTAL_CRUSHER_BE, pos, state);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.permafrostpeaks.crystal_crusher");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CrystalCrusherScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);

        nbt.putInt("crystal_crusher.progress", progress);
        nbt.putInt("crystal_crusher.burn_time", burnTime);
        nbt.putInt("crystal_crusher.max_burn_time", maxBurnTime);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, inventory, registryLookup);

        progress = nbt.getInt("crystal_crusher.progress");
        burnTime = nbt.getInt("crystal_crusher.burn_time");
        maxBurnTime = nbt.getInt("crystal_crusher.max_burn_time");

        super.readNbt(nbt, registryLookup);
    }

    public void tick(World world, BlockPos pos, BlockState state) {

        boolean dirty = false;

        // Decrease burn time
        if (burnTime > 0) {
            burnTime--;
            dirty = true;
        }

        // Try to consume fuel
        if (burnTime == 0 && hasRecipe()) {
            ItemStack fuelStack = getStack(FUEL_SLOT);
            int fuelValue = getFuelTime(fuelStack);

            if (fuelValue > 0) {
                burnTime = fuelValue;
                maxBurnTime = fuelValue;

                fuelStack.decrement(1);

                if (fuelStack.isEmpty()) {
                    setStack(FUEL_SLOT, ItemStack.EMPTY);
                }

                dirty = true;
            }
        }

        // Crafting only while burning
        if (burnTime > 0 && hasRecipe()) {
            progress++;

            if (progress >= maxProgress) {
                craftItem();
                progress = 0;
            }

            dirty = true;
        } else if (!hasRecipe()) {
            progress = 0;
            dirty = true;
        }

        if (dirty) {
            markDirty(world, pos, state);
        }
    }

    private int getFuelTime(ItemStack stack) {
        return AbstractFurnaceBlockEntity.createFuelTimeMap()
                .getOrDefault(stack.getItem(), 0);
    }

    private CrusherRecipe getRecipe(ItemStack input) {
        for (CrusherRecipe recipe : RECIPES) {
            if (input.isOf(recipe.input())) {
                return recipe;
            }
        }
        return null;
    }

    private ItemStack getRecipeOutput(ItemStack input) {
        CrusherRecipe recipe = getRecipe(input);
        if (recipe == null) return ItemStack.EMPTY;

        return new ItemStack(recipe.output(), recipe.count());
    }

    private boolean hasRecipe() {
        ItemStack output = getRecipeOutput(getStack(INPUT_SLOT));

        return !output.isEmpty()
                && canInsertAmountIntoOutput(output.getCount())
                && canInsertItemIntoOutput(output);
    }

    private void craftItem() {
        ItemStack input = getStack(INPUT_SLOT);
        CrusherRecipe recipe = getRecipe(input);

        if (recipe == null) return;

        removeStack(INPUT_SLOT, 1);

        ItemStack outputStack = getStack(OUTPUT_SLOT);
        ItemStack result = new ItemStack(recipe.output(), recipe.count());

        setStack(OUTPUT_SLOT,
                new ItemStack(result.getItem(),
                        outputStack.getCount() + result.getCount()));
    }

    private boolean canInsertItemIntoOutput(ItemStack output) {
        return getStack(OUTPUT_SLOT).isEmpty()
                || getStack(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutput(int count) {
        int max = getStack(OUTPUT_SLOT).isEmpty()
                ? 64
                : getStack(OUTPUT_SLOT).getMaxCount();

        return max >= getStack(OUTPUT_SLOT).getCount() + count;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}