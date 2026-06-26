package net.mod.permafrostpeaks.screen.custom;

import net.mod.permafrostpeaks.block.entity.custom.CrystalCrusherBlockEntity;
import net.mod.permafrostpeaks.screen.ModScreenHandlers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

public class CrystalCrusherScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final CrystalCrusherBlockEntity blockEntity;

    // Client constructor
    public CrystalCrusherScreenHandler(int syncId, PlayerInventory inventory, BlockPos pos) {
        this(syncId, inventory,
                inventory.player.getWorld().getBlockEntity(pos),
                new ArrayPropertyDelegate(4));
    }

    // Server constructor
    public CrystalCrusherScreenHandler(int syncId, PlayerInventory playerInventory,
                                       BlockEntity blockEntity, PropertyDelegate delegate) {

        super(ModScreenHandlers.CRYSTAL_CRUSHER_SCREEN_HANDLER, syncId);

        this.inventory = (Inventory) blockEntity;
        this.blockEntity = (CrystalCrusherBlockEntity) blockEntity;
        this.propertyDelegate = delegate;


        // Input
        this.addSlot(new Slot(inventory, 0, 104, 10));

        // Fuel
        this.addSlot(new Slot(inventory, 1, 55, 41));

        // Output
        this.addSlot(new Slot(inventory, 2, 104, 53));

        // Player inventory
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(delegate);
    }


    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledCrushingWheelsProgress() {
        int progress = propertyDelegate.get(0);
        int maxProgress = propertyDelegate.get(1);
        int size = 17;

        return maxProgress != 0 && progress != 0
                ? progress * size / maxProgress
                : 0;
    }

    public boolean isBurning() {
        return propertyDelegate.get(2) > 0;
    }

    public int getScaledFuelProgress() {
        int burnTime = propertyDelegate.get(2);
        int maxBurnTime = propertyDelegate.get(3);

        int height = 14;

        return maxBurnTime != 0 && burnTime != 0
                ? burnTime * height / maxBurnTime
                : 0;
    }


    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            int machineSize = 3;

            if (invSlot < machineSize) {
                if (!this.insertItem(originalStack, machineSize, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.insertItem(originalStack, 0, machineSize, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // -----------------------
    // PLAYER INVENTORY UI
    // -----------------------

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}