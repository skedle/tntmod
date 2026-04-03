package net.corey.tntmod.item;

import net.corey.tntmod.Tntmod;
import net.corey.tntmod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier WORLD_ESSENCE = TierSortingRegistry.registerTier(
            new ForgeTier(5,3000,10f,17f,20,
                    ModTags.Blocks.NEEDS_WORLD_ESSENCE_TOOL, () -> Ingredient.of(ModItems.WORLD_ESSENCE.get())),
            new ResourceLocation(Tntmod.MODID, "world_essence"), List.of(Tiers.NETHERITE), List.of());

}
