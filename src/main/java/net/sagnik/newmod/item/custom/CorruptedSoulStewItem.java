package net.sagnik.newmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Random;

public class CorruptedSoulStewItem extends Item {

    public CorruptedSoulStewItem() {
        super(new Item.Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(10)
                        .saturationMod(1.5f)
                        .alwaysEat()
                        .build()
                )
                .stacksTo(1)
        );
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide() && pLivingEntity instanceof Player player) {
            Random random = new Random();

            MobEffect[] goodEffects = {
                    MobEffects.REGENERATION,
                    MobEffects.DAMAGE_BOOST,
                    MobEffects.MOVEMENT_SPEED,
                    MobEffects.ABSORPTION,
                    MobEffects.FIRE_RESISTANCE,
                    MobEffects.NIGHT_VISION
            };

            MobEffect[] badEffects = {
                    MobEffects.POISON,
                    MobEffects.BLINDNESS,
                    MobEffects.WEAKNESS,
                    MobEffects.CONFUSION,
                    MobEffects.WITHER,
                    MobEffects.LEVITATION
            };

            MobEffect goodEffect = goodEffects[random.nextInt(goodEffects.length)];
            MobEffect badEffect = badEffects[random.nextInt(badEffects.length)];

            player.addEffect(new MobEffectInstance(goodEffect, 400, 1));
            player.addEffect(new MobEffectInstance(badEffect, 200, 0));

            // 10% chance of strong wither
            if (random.nextFloat() < 0.10f) {
                player.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 2));
                player.sendSystemMessage(Component.literal("§4The corruption consumes you..."));
            }

            // 5% chance of full heal
            if (random.nextFloat() < 0.05f) {
                player.setHealth(player.getMaxHealth());
                player.sendSystemMessage(Component.literal("§5The soul energy surges through you!"));
            }
        }

        return new ItemStack(Items.BOWL);
    }
}