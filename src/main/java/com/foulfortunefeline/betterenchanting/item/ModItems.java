package com.foulfortunefeline.betterenchanting.item;

import net.minecraft.item.Item;
import com.foulfortunefeline.betterenchanting.BetterEnchanting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(BetterEnchanting.MOD_ID, name), item);
    }

    public static void registerModItems() {
        BetterEnchanting.BETTER_ENCHANTING.info("Registering Mod Items for " + BetterEnchanting.MOD_ID);
    }
}
