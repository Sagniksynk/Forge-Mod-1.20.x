package net.sagnik.newmod.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.sagnik.newmod.capability.GunCapabilityProvider;
import net.sagnik.newmod.item.custom.guns.GunItem;
import net.sagnik.newmod.network.ModMessages;

import java.util.function.Supplier;

public class ReloadPacket {

    private final String gunType;

    public ReloadPacket(String gunType) { this.gunType = gunType; }

    public static void encode(ReloadPacket packet, FriendlyByteBuf buf) {
        buf.writeUtf(packet.gunType);
    }

    public static ReloadPacket decode(FriendlyByteBuf buf) {
        return new ReloadPacket(buf.readUtf());
    }

    public static void handle(ReloadPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            player.getCapability(GunCapabilityProvider.GUN_CAP).ifPresent(cap -> {
                if (cap.isReloading()) return;

                // Use packet.gunType instead of just gunType
                int maxAmmo = switch (packet.gunType) {
                    case "pistol" -> GunItem.PISTOL_MAX_AMMO;
                    case "rifle" -> GunItem.RIFLE_MAX_AMMO;
                    case "shotgun" -> GunItem.SHOTGUN_MAX_AMMO;
                    default -> 0;
                };

                cap.setAmmo(packet.gunType, maxAmmo);
                cap.setReloading(false);

                ModMessages.INSTANCE.send(
                        net.minecraftforge.network.PacketDistributor.PLAYER.with(() -> player),
                        new AmmoDataPacket(packet.gunType, maxAmmo));
            });
        });
        ctx.get().setPacketHandled(true);
    }
}