package net.tntmaster.tntmod.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.tntmaster.tntmod.Tntmod;
import net.tntmaster.tntmod.networking.packet.*;

public class ModPackets {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Tntmod.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(ExampleC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ExampleC2SPacket::new)
                .encoder(ExampleC2SPacket::toBytes)
                .consumerMainThread(ExampleC2SPacket::handle)
                .add();

        net.messageBuilder(PlayTapeS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PlayTapeS2CPacket::new)
                .encoder(PlayTapeS2CPacket::toBytes)
                .consumerMainThread(PlayTapeS2CPacket::handle)
                .add();

        net.messageBuilder(StopTapeS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(StopTapeS2CPacket::new)
                .encoder(StopTapeS2CPacket::toBytes)
                .consumerMainThread(StopTapeS2CPacket::handle)
                .add();
        net.messageBuilder(RemoveTapeC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(RemoveTapeC2SPacket::new)
                .encoder(RemoveTapeC2SPacket::toBytes)
                .consumerMainThread(RemoveTapeC2SPacket::handle)
                .add();
    }
    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}


