package com.foulfortunefeline.fancybookshelf;

import com.foulfortunefeline.fancybookshelf.block.ModBlocks;
import com.foulfortunefeline.fancybookshelf.item.ModItems;
import com.foulfortunefeline.fancybookshelf.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FancyBookshelf implements ModInitializer {
	public static final String MOD_ID = "fancybookshelf";
	public static final Logger FANCY_BOOKSHELF = LogManager.getLogger(MOD_ID);



	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		FANCY_BOOKSHELF.info("Initializing " + MOD_ID + "...");
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModScreenHandlers.registerScreenHandlers();
	}
}
