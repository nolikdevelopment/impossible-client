package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.event.impl.PacketEvent;
import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.network.protocol.game.ServerboundChatPacket;

public class AntiSpam extends Module {
    private String[] text = new String[] {
            "hhtps:", ".ru", ".com", ".net", ".me", ".org", ".xyz"
    };
    public AntiSpam() {
        super("AntiSpam", "Just anti spam", Category.MISC);
    }
    @Subscribe public void onPacket(PacketEvent.Receive event) {
        if (event.getPacket() instanceof ServerboundChatPacket packet) {
            for (String string : text) {
                if (packet.message().contains(string)) {
                    event.cancel();
                }
            }
        }
    }
}
