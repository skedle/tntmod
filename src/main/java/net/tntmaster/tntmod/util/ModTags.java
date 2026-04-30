package net.tntmaster.tntmod.util;

import net.tntmaster.tntmod.Tntmod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_WORLD_ESSENCE_TOOL = tag("needs_world_essance_tool");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Tntmod.MODID, name));
        }
    }

    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Tntmod.MODID, name));
        }
    }
}