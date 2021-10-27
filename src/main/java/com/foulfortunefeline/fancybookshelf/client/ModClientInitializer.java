package com.foulfortunefeline.fancybookshelf.client;

import com.foulfortunefeline.fancybookshelf.FancyBookshelf;
import com.foulfortunefeline.fancybookshelf.models.FancyBookshelfModelProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;

@Environment(EnvType.CLIENT)
public record ModClientInitializer() implements net.fabricmc.api.ClientModInitializer {

    public static final ModelResourceProvider modelProvider = new FancyBookshelfModelProvider();

    @Override
    public void onInitializeClient() {
        FancyBookshelf.FANCY_BOOKSHELF.info("Initializing " + FancyBookshelf.MOD_ID + " client...");
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm -> modelProvider);

//        ModScreenHandlers.registerScreenHandlers();
    }
}
