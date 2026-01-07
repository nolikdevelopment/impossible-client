package me.nolikdevelopment.impossibleclient.features.modules.misc;

import me.nolikdevelopment.impossibleclient.event.impl.PacketEvent;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;

public class AntiHunger extends Module {
    public AntiHunger() {
        super("AntiHunger", "Partially prevents loss of hunger", Category.MISC);
    }
    @Subscribe public void onPacket(PacketEvent.Send event) {
        if (event.getPacket() instanceof ServerboundPlayerCommandPacket packet) {
            if (packet.getAction() == ServerboundPlayerCommandPacket.Action.STOP_SPRINTING || packet.getAction() == ServerboundPlayerCommandPacket.Action.START_SPRINTING) {
                event.cancel();
            }
        }
    }
}
