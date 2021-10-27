package com.foulfortunefeline.fancybookshelf.models;

import com.foulfortunefeline.fancybookshelf.FancyBookshelf;
import com.foulfortunefeline.fancybookshelf.block.book_case.BookCaseEntity;
import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class FancyBookshelfModelProvider implements ModelResourceProvider {
    public static final Identifier CASE = new Identifier(FancyBookshelf.MOD_ID, "block/book_case/case");
    public static final Identifier BOOK_CASE = new Identifier(FancyBookshelf.MOD_ID, "block/book_case/book_case");
    public static final Identifier[] BOOKS = new Identifier[BookCaseEntity.INV_SIZE];
    static {
        for (int i = 0; i < BookCaseEntity.INV_SIZE; i++) {
            BOOKS[i] = new Identifier(FancyBookshelf.MOD_ID, "block/book_case/book_" + i);
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
