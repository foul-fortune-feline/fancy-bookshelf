package com.foulfortunefeline.fancybookshelf.block.book_case;

import com.foulfortunefeline.fancybookshelf.block.ModBlocks;
import com.foulfortunefeline.fancybookshelf.inventory.ImplementedInventory;
import com.foulfortunefeline.fancybookshelf.screen.BookCaseScreenHandler;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import org.jetbrains.annotations.Nullable;

public class BookCaseEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory
        , BlockEntityClientSerializable {
    public static final int INV_SIZE = 18;
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(INV_SIZE, ItemStack.EMPTY);
    private final BookCase bookCase;


    public BookCaseEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.BOOK_CASE_ENTITY, pos, state);
        bookCase = (BookCase) state.getBlock();

    }


    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new BookCaseScreenHandler(syncId, inv, this);
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, this.inventory);
        if (world != null && !world.isClient()) { sync(); }
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
        if (world != null && !world.isClient()) { sync(); }
        return nbt;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        ImplementedInventory.super.setStack(slot, stack);

        if (world != null && !world.isClient()) {
            this.sync();
        }
    }

    @Override
    public void fromClientTag(NbtCompound tag) {
        inventory.clear();
        Inventories.readNbt(tag, inventory);

        // Tell the game to re-render the Chunk Section
        assert world != null;
        ((ClientWorld) world).scheduleBlockRenders(
                ChunkSectionPos.getSectionCoord(this.getPos().getX()),
                ChunkSectionPos.getSectionCoord(this.getPos().getY()),
                ChunkSectionPos.getSectionCoord(this.getPos().getY())
        );
    }


    @Override
    public NbtCompound toClientTag(NbtCompound tag) {
        if (world != null && world.isClient){
            return tag;
        }
        return Inventories.writeNbt(tag, inventory);
    }
}
