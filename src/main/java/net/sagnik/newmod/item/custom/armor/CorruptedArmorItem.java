package net.sagnik.newmod.item.custom.armor;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sagnik.newmod.NewMod;

public class CorruptedArmorItem extends ArmorItem {

    public CorruptedArmorItem(ArmorMaterial material, Type type, Properties properties) {
        super(material, type, properties);
    }

    // Move the full set logic to a separate event handler class
    @Mod.EventBusSubscriber(modid = NewMod.MOD_ID)
    public static class ArmorEffectHandler {

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if (event.phase != TickEvent.Phase.END) return;
            if (event.player.level().isClientSide()) return;

            Player player = event.player;

            if (hasFullSet(player)) {
                applyFullSetBonus(player);
            }
        }

        private static boolean hasFullSet(Player player) {
            return player.getInventory().armor.get(3).getItem() instanceof CorruptedArmorItem
                    && player.getInventory().armor.get(2).getItem() instanceof CorruptedArmorItem
                    && player.getInventory().armor.get(1).getItem() instanceof CorruptedArmorItem
                    && player.getInventory().armor.get(0).getItem() instanceof CorruptedArmorItem;
        }

        private static void applyFullSetBonus(Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 30, 1, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30, 0, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 30, 0, false, false));
        }
    }
}