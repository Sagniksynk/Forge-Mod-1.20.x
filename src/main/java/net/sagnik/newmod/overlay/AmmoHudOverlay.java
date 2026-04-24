package net.sagnik.newmod.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sagnik.newmod.NewMod;
import net.sagnik.newmod.capability.GunCapabilityProvider;
import net.sagnik.newmod.item.custom.guns.GunItem;

@Mod.EventBusSubscriber(modid = NewMod.MOD_ID, value = Dist.CLIENT)
public class AmmoHudOverlay {

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        if (event.getOverlay() != VanillaGuiOverlay.HOTBAR.type()) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        ItemStack held = mc.player.getMainHandItem();
        if (!(held.getItem() instanceof GunItem gun)) return;

        mc.player.getCapability(GunCapabilityProvider.GUN_CAP).ifPresent(cap -> {
            int ammo = cap.getAmmo(gun.getGunType());
            int maxAmmo = switch (gun.getGunType()) {
                case "pistol" -> GunItem.PISTOL_MAX_AMMO;
                case "rifle" -> GunItem.RIFLE_MAX_AMMO;
                case "shotgun" -> GunItem.SHOTGUN_MAX_AMMO;
                default -> 0;
            };

            GuiGraphics graphics = event.getGuiGraphics();
            int screenWidth = mc.getWindow().getGuiScaledWidth();
            int screenHeight = mc.getWindow().getGuiScaledHeight();

            // Draw ammo background box
            graphics.fill(screenWidth - 80, screenHeight - 45,
                    screenWidth - 5,  screenHeight - 20, 0x88000000);

            // Draw ammo text
            String ammoText = ammo + " / " + maxAmmo;
            String label = gun.getGunType().toUpperCase();

            graphics.drawString(mc.font, label,
                    screenWidth - 75, screenHeight - 42, 0xFFAAAAAA);
            graphics.drawString(mc.font, ammoText,
                    screenWidth - 75, screenHeight - 30,
                    ammo == 0 ? 0xFFFF4444 : 0xFFFFFFFF);  // red when empty
        });
    }
}