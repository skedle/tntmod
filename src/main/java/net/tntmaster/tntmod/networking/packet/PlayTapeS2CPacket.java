package net.tntmaster.tntmod.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.tntmaster.tntmod.item.custom.TapeItem;
import net.tntmaster.tntmod.sound.custom.TapeSound;

import java.util.function.Supplier;

public class PlayTapeS2CPacket {

    private final BlockPos pPos;
    private final ItemStack tapeItem;



    public PlayTapeS2CPacket(FriendlyByteBuf buf) {
        pPos = buf.readBlockPos();
        tapeItem = buf.readItem();

    }

    public PlayTapeS2CPacket(BlockPos pPos, ItemStack tapeItem) {
        this.pPos = pPos;
        this.tapeItem = tapeItem;

    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pPos);
        buf.writeItemStack(tapeItem, false);

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if (tapeItem.getItem() instanceof TapeItem) {
                TapeSound tapeSound = TapeSound.getInstance();
                tapeSound.playTape(pPos, (TapeItem) tapeItem.getItem());

            }

        });
        return true;
    }


}
