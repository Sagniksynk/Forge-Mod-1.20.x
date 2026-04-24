package net.sagnik.newmod.capability;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sagnik.newmod.NewMod;

@Mod.EventBusSubscriber(modid = NewMod.MOD_ID)
public class GunCapabilityHandler {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<net.minecraft.world.entity.Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(
                    new ResourceLocation(NewMod.MOD_ID, "gun_capability"),
                    new GunCapabilityProvider()
            );
        }
    }

    // Copy capability on player respawn
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().reviveCaps();
            event.getOriginal().getCapability(GunCapabilityProvider.GUN_CAP).ifPresent(oldCap ->
                    event.getEntity().getCapability(GunCapabilityProvider.GUN_CAP).ifPresent(newCap ->
                            newCap.deserializeNBT(oldCap.serializeNBT())
                    )
            );
            event.getOriginal().invalidateCaps();
        }
    }
}