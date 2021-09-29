package com.foulfortunefeline.betterenchanting.models;

import com.foulfortunefeline.betterenchanting.BetterEnchanting;
import com.foulfortunefeline.betterenchanting.block.book_case.BookCaseEntity;
import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class BetterEnchantingModelProvider implements ModelResourceProvider {
    public static final Identifier CASE = new Identifier(BetterEnchanting.MOD_ID, "block/book_case/case");
    public static final Identifier BOOK_CASE = new Identifier(BetterEnchanting.MOD_ID, "block/book_case/book_case");
    public static final Identifier[] BOOKS = new Identifier[BookCaseEntity.INV_SIZE];
    static {
        for (int i = 0; i < BookCaseEntity.INV_SIZE; i++) {
            BOOKS[i] = new Identifier(BetterEnchanting.MOD_ID, "block/book_case/book_" + i);
        }
    }


    @Override
    public @Nullable UnbakedModel loadModelResource(Identifier resourceId, ModelProviderContext context) {
        if(resourceId.equals(BOOK_CASE)) {
            return new UnbakedBookCaseModel(context);
        } else {
            return null;
        }
    }
}
