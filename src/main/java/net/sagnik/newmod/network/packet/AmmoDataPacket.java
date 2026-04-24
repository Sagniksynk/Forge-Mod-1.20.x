package net.sagnik.newmod.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.sagnik.newmod.capability.GunCapabilityProvider;

import java.util.function.Supplier;

public class AmmoDataPacket {

    private final String gunType;
    private final int ammo;

    public AmmoDataPacket(String gunType, int ammo) {
        this.gunType = gunType;
        this.ammo = ammo;
    }

    public static void encode(AmmoDataPacket packet, FriendlyByteBuf buf) {
        buf.writeUtf(packet.gunType);
        buf.writeInt(packet.ammo);
    }

    public static AmmoDataPacket decode(FriendlyByteBuf buf) {
        return new AmmoDataPacket(buf.readUtf(), buf.readInt());
    }

    public static void handle(AmmoDataPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            var player = Minecraft.getInstance().player;
            if (player == null) return;
            player.getCapability(GunCapabilityProvider.GUN_CAP).ifPresent(cap ->
                    cap.setAmmo(packet.gunType, packet.ammo)
            );
        });
        ctx.get().setPacketHandled(true);
    }
}