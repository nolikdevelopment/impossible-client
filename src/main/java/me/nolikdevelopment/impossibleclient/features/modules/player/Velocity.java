package me.nolikdevelopment.impossibleclient.features.modules.player;

import me.nolikdevelopment.impossibleclient.event.impl.PacketEvent;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import net.minecraft.network.protocol.game.ClientboundExplodePacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;

public class Velocity extends Module {
    public Velocity() {
        super("Velocity", "Removes velocity from explosions and entities", Category.PLAYER);
    }

    @Subscribe
    private void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof ClientboundSetEntityMotionPacket || event.getPacket() instanceof ClientboundExplodePacket)
            event.cancel();
    }
}
