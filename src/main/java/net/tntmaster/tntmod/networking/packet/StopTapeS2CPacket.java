package net.tntmaster.tntmod.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.tntmaster.tntmod.sound.custom.TapeSound;

import java.util.function.Supplier;

public class StopTapeS2CPacket {
    private final BlockPos pPos;

    public StopTapeS2CPacket(FriendlyByteBuf buf) {
        pPos = buf.readBlockPos();

    }

    public StopTapeS2CPacket(BlockPos pPos) {
        this.pPos = pPos;

    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pPos);

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            TapeSound tapeSound = TapeSound.getInstance();
            tapeSound.stopTape(pPos);


        });
        return true;
    }
}
