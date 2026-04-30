package net.tntmaster.tntmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties PARASITE = new FoodProperties.Builder().nutrition(2).saturationMod(0.1f).build();

    public static final FoodProperties LEDGBAR = new FoodProperties.Builder().nutrition(12).saturationMod(1.2f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 3), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 2), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1), 1f).build();
}
