package net.mod.permafrostpeaks.block.entity.custom;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.mod.permafrostpeaks.block.entity.ImplementedInventory;
import net.mod.permafrostpeaks.block.entity.ModBlockEntities;
import net.mod.permafrostpeaks.item.ModItems;
import net.mod.permafrostpeaks.screen.custom.CrystalCrusherScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
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

public class CrystalCrusherBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    private record CrusherRecipe(Item input, Item output, int count) {}

    private static final List<CrusherRecipe> RECIPES = List.of(
            new CrusherRecipe(ModItems.GREEN_CRYSTAL_SHARD, ModItems.BLUE_CRYSTAL_SHARD, 6),
            new CrusherRecipe(ModItems.RED_CRYSTAL_SHARD, ModItems.RED_CRYSTAL_SHARD, 4),
            new CrusherRecipe(ModItems.YELLOW_CRYSTAL_SHARD, ModItems.GREEN_CRYSTAL_SHARD, 2),
            new CrusherRecipe(ModItems.BLUE_CRYSTAL_SHARD, ModItems.YELLOW_CRYSTAL_SHARD, 8)
    );

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public CrystalCrusherBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRYSTAL_CRUSHER_BE, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CrystalCrusherBlockEntity.this.progress;
                    case 1 -> CrystalCrusherBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CrystalCrusherBlockEntity.this.progress = value;
                    case 1 -> CrystalCrusherBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.permafrostpeaks.crystal_crusher");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CrystalCrusherScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("crystal_crusher.progress", progress);
        nbt.putInt("crystal_crusher.max_progress", maxProgress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("crystal_crusher.progress");
        maxProgress = nbt.getInt("crystal_crusher.max_progress");
        super.readNbt(nbt, registryLookup);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (hasRecipe()) {
            increaseCraftingProgress();
            markDirty(world, pos, state);

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 72;
    }

    // -----------------------------
    // Recipe lookup
    // -----------------------------
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

    private void craftItem() {
        ItemStack input = this.getStack(INPUT_SLOT);
        CrusherRecipe recipe = getRecipe(input);

        if (recipe == null) return;

        ItemStack output = new ItemStack(recipe.output(), recipe.count());

        this.removeStack(INPUT_SLOT, 1);

        this.setStack(
                OUTPUT_SLOT,
                new ItemStack(
                        output.getItem(),
                        this.getStack(OUTPUT_SLOT).getCount() + output.getCount()
                )
        );
    }

    private boolean hasRecipe() {
        ItemStack output = getRecipeOutput(this.getStack(INPUT_SLOT));

        return !output.isEmpty()
                && canInsertAmountIntoOutputSlot(output.getCount())
                && canInsertItemIntoOutputSlot(output);
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(OUTPUT_SLOT).isEmpty()
                || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = this.getStack(OUTPUT_SLOT).isEmpty()
                ? 64
                : this.getStack(OUTPUT_SLOT).getMaxCount();

        int currentCount = this.getStack(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
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