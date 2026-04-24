package net.sagnik.newmod.keybinding;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sagnik.newmod.NewMod;
import net.sagnik.newmod.item.custom.guns.GunItem;
import net.sagnik.newmod.network.ModMessages;
import net.sagnik.newmod.network.packet.ReloadPacket;

@Mod.EventBusSubscriber(modid = NewMod.MOD_ID, value = Dist.CLIENT)
public class KeyInputHandler {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        while (ModKeyBindings.RELOAD_KEY.consumeClick()) {
            ItemStack held = mc.player.getMainHandItem();
            if (held.getItem() instanceof GunItem gun) {
                // Call getGunType() on the instance, not statically
                ModMessages.INSTANCE.sendToServer(new ReloadPacket(gun.getGunType()));
            }
        }
    }
}