package net.sagnik.newmod.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.sagnik.newmod.NewMod;
import net.sagnik.newmod.network.packet.AmmoDataPacket;
import net.sagnik.newmod.network.packet.ReloadPacket;

public class ModMessages {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(NewMod.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;

    public static void register() {
        INSTANCE.registerMessage(packetId++, ReloadPacket.class,
                ReloadPacket::encode, ReloadPacket::decode, ReloadPacket::handle);
        INSTANCE.registerMessage(packetId++, AmmoDataPacket.class,
                AmmoDataPacket::encode, AmmoDataPacket::decode, AmmoDataPacket::handle);
    }
}