package com.foulfortunefeline.betterenchanting.item;

import com.foulfortunefeline.betterenchanting.BetterEnchanting;
import net.fabricmc.fabric.impl.tag.extension.TagFactoryImpl;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class BetterEnchantingItemTags {
    public static final Tag<Item> BOOKS = create(new Identifier(BetterEnchanting.MOD_ID, "books"));


    public static Tag<Item> create(Identifier id) {
        return TagFactoryImpl.ITEM.create(id);
    }
}
