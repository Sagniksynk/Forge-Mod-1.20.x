package net.sagnik.newmod.client;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sagnik.newmod.NewMod;
import net.sagnik.newmod.entity.BulletEntity;

@Mod.EventBusSubscriber(modid = NewMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(BulletEntity.BULLET.get(),
                context -> new ThrownItemRenderer<>(context, 0.5f, false));
    }
}