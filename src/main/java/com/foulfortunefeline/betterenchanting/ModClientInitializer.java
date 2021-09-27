package com.foulfortunefeline.betterenchanting;

import com.foulfortunefeline.betterenchanting.BetterEnchanting;
import com.foulfortunefeline.betterenchanting.screen.BookCaseScreen;
import com.foulfortunefeline.betterenchanting.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class ModClientInitializer implements net.fabricmc.api.ClientModInitializer {


    @Override
    public void onInitializeClient() {
        BetterEnchanting.BETTER_ENCHANTING.info("Initializing " + BetterEnchanting.MOD_ID + " client...");
        ModScreenHandlers.registerScreenHandlers();
        ScreenRegistry.register(ModScreenHandlers.BOOK_CASE_SCREEN_HANDLER, BookCaseScreen::new);
    }
}
