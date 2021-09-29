package com.foulfortunefeline.betterenchanting.client;

import com.foulfortunefeline.betterenchanting.BetterEnchanting;
import com.foulfortunefeline.betterenchanting.models.BetterEnchantingModelProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;

@Environment(EnvType.CLIENT)
public record ModClientInitializer() implements net.fabricmc.api.ClientModInitializer {

    public static final ModelResourceProvider modelProvider = new BetterEnchantingModelProvider();

    @Override
    public void onInitializeClient() {
        BetterEnchanting.BETTER_ENCHANTING.info("Initializing " + BetterEnchanting.MOD_ID + " client...");
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm -> modelProvider);

//        ModScreenHandlers.registerScreenHandlers();
    }
}
