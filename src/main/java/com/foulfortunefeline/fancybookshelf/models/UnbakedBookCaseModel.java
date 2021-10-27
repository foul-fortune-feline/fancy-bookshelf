package com.foulfortunefeline.fancybookshelf.models;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class UnbakedBookCaseModel implements UnbakedModel {
    private final List<UnbakedModel> books;

    public UnbakedBookCaseModel(ModelProviderContext ctx) {
        books = new ArrayList<>();
        for (Identifier model : FancyBookshelfModelProvider.BOOKS) {
            books.add(ctx.loadModel(model));
        }
    }

    @Override
    public Collection<SpriteIdentifier> getTextureDependencies(Function<Identifier, UnbakedModel> unbakedModelGetter
            , Set<Pair<String, String>> unresolvedTextureReferences) {
        List<SpriteIdentifier> result = new ArrayList<>();
        for (UnbakedModel m : books) {
            result.addAll(m.getTextureDependencies(unbakedModelGetter, unresolvedTextureReferences));
        }
        return result;
    }

    @Override
    public Collection<Identifier> getModelDependencies() {
        ArrayList<Identifier> result = new ArrayList<>(List.of(FancyBookshelfModelProvider.CASE));
        result.addAll(List.of(FancyBookshelfModelProvider.BOOKS));
        return result;
    }

    @Override
    public BakedModel bake(ModelLoader loader, Function<SpriteIdentifier, Sprite> textureGetter
            , ModelBakeSettings rotationContainer, Identifier identifier) {

        BakedModel case_model = loader.bake(FancyBookshelfModelProvider.CASE, rotationContainer);
        BakedModel[] book_models = new BakedModel[18];
        for (int i = 0; i < 18; i++) {
            book_models[i] = books.get(i).bake(loader, textureGetter, rotationContainer, identifier);
        }
        return new BakedBookCaseModel(case_model, book_models);
    }


}