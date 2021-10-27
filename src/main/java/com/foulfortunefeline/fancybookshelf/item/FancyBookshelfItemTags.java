package com.foulfortunefeline.fancybookshelf.item;

import com.foulfortunefeline.fancybookshelf.FancyBookshelf;
import net.fabricmc.fabric.impl.tag.extension.TagFactoryImpl;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class FancyBookshelfItemTags {
    public static final Tag<Item> BOOKS = create(new Identifier(FancyBookshelf.MOD_ID, "books"));


    public static Tag<Item> create(Identifier id) {
        return TagFactoryImpl.ITEM.create(id);
    }
}
