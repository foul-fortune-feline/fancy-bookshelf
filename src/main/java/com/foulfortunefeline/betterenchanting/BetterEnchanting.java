package com.foulfortunefeline.betterenchanting;

import com.foulfortunefeline.betterenchanting.block.ModBlocks;
import com.foulfortunefeline.betterenchanting.block.bookcase.BookCase;
import com.foulfortunefeline.betterenchanting.item.ModItems;
import com.foulfortunefeline.betterenchanting.screen.BookCaseScreenHandler;
import com.foulfortunefeline.betterenchanting.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetterEnchanting implements ModInitializer {
	public static final String MOD_ID = "betterenchanting";
	public static final Logger BETTER_ENCHANTING = LogManager.getLogger(MOD_ID);



	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		BETTER_ENCHANTING.info("Initializing " + MOD_ID + "...");
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModScreenHandlers.registerScreenHandlers();
	}
}
