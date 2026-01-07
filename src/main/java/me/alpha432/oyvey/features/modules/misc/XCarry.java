package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.event.impl.PacketEvent;
import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.network.protocol.game.ServerboundContainerClosePacket;

public class XCarry extends Module {
    public XCarry() {
        super("XCarry", "Allows you to store items in all slots", Category.MISC);
    }
    @Subscribe public void onPacket(PacketEvent.Send event) {
        if (event.getPacket() instanceof ServerboundContainerClosePacket packet) {
            if (mc.player.containerMenu.containerId == packet.getContainerId()) {
                event.cancel();
            }
        }
    }
}
