package com.foulfortunefeline.fancybookshelf.item;

import com.foulfortunefeline.fancybookshelf.FancyBookshelf;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(FancyBookshelf.MOD_ID, name), item);
    }

    public static void registerModItems() {
        FancyBookshelf.FANCY_BOOKSHELF.info("Registering Mod Items for " + FancyBookshelf.MOD_ID);
    }
}
