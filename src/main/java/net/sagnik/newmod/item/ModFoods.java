package net.sagnik.newmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties SAPPHIRE_BERRY = new FoodProperties.Builder()
            .nutrition(4)
            .saturationMod(0.5f)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1), 0.5f)
            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 200, 1), 0.5f)
            .build();
    public static final FoodProperties CORRUPTED_SOUL_STEW = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(1.5f)
            .alwaysEat()
            .build();
    public static final FoodProperties MYSTERIOUS_FLESH = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(0.1f)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 100, 0), 0.8f)
            .build();
}