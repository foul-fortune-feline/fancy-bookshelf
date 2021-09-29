package com.foulfortunefeline.betterenchanting.screen;

import com.foulfortunefeline.betterenchanting.block.book_case.BookCaseEntity;
import com.foulfortunefeline.betterenchanting.item.BetterEnchantingItemTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class BookCaseScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public BookCaseScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(BookCaseEntity.INV_SIZE));
    }

    public BookCaseScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.BOOK_CASE_SCREEN_HANDLER, syncId);
        checkSize(inventory, BookCaseEntity.INV_SIZE);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);

        int m;
        int l;
        // Book Case Inventory
        for (m = 0; m < 2; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new BookSlot(inventory, l + m * 9, 8 + l * 18, 28 + m * 18));
            }
        }
        // Player Inventory
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        // The Player Hotbar
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }
    }


    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // Shift + Player inv Slot
    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = new ItemStack(originalStack.getItem());
            if (index < this.inventory.size()) {
                if (!insertItem(originalStack, BookCaseEntity.INV_SIZE, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!insertItem(originalStack, 0, BookCaseEntity.INV_SIZE, false)) {
                return ItemStack.EMPTY;
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
    protected boolean insertItem(ItemStack stack, int startIndex, int endIndex, boolean fromLast) {
        if (endIndex > BookCaseEntity.INV_SIZE){
            return super.insertItem(stack, startIndex, endIndex, fromLast);
        }
        boolean result = false;
        int i = startIndex;
        if (fromLast) {
            i = endIndex - 1;
        }

        Slot slot2;
        ItemStack itemStack;
        while (!stack.isEmpty()) {
            if (fromLast) {
                if (i < startIndex) {
                    break;
                }
            } else if (i >= endIndex) {
                break;
            }

            slot2 = slots.get(i);
            itemStack = slot2.getStack();
            if (itemStack.isEmpty() && slot2.canInsert(stack)) {
                slot2.setStack(new ItemStack(stack.getItem()));
                stack.decrement(1);

                slot2.markDirty();
                result = true;
            }

            if (fromLast) {
                i--;
            } else {
                i++;
            }
        }
        return result;
    }

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return !slot.hasStack() && super.canInsertIntoSlot(stack, slot);
    }

    public class BookSlot extends Slot {

        public BookSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public int getMaxItemCount() {
            return 1;
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return stack.isIn(BetterEnchantingItemTags.BOOKS);
        }

    }
}
