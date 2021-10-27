package com.foulfortunefeline.fancybookshelf.models;

import com.foulfortunefeline.fancybookshelf.block.book_case.BookCaseEntity;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

public class BakedBookCaseModel implements BakedModel, FabricBakedModel {
    private final BakedModel CASE;
    private final BakedModel[] BOOKS;

    public BakedBookCaseModel(BakedModel case_model, BakedModel[] book_models) {
        this.CASE = case_model;
        this.BOOKS = book_models;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction face, Random random) {
        return null;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean hasDepth() {
        return true;
    }

    @Override
    public boolean isSideLit() {
        return false;
    }

    @Override
    public boolean isBuiltin() {
        return false;
    }

    @Override
    public Sprite getParticleSprite() {
        return CASE.getParticleSprite();
    }

    @Override
    public ModelTransformation getTransformation() {
        return null;
    }

    @Override
    public ModelOverrideList getOverrides() {
        return null;
    }

    @Override
    public boolean isVanillaAdapter() {
        return false;
    }


    @Override
    public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos
            , Supplier<Random> randomSupplier, @NotNull RenderContext context) {
        context.fallbackConsumer().accept(CASE);
        DefaultedList<ItemStack> inv = ((BookCaseEntity) Objects.requireNonNull(blockView.getBlockEntity(pos))).getItems();
        for (int i = 0; i < BookCaseEntity.INV_SIZE; i++) {
            if (!inv.get(i).isEmpty()) {
                context.fallbackConsumer().accept(BOOKS[i]);
            }
        }
    }

    @Override
    public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {}
}