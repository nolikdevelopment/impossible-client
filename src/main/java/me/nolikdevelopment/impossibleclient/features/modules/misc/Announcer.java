package me.nolikdevelopment.impossibleclient.features.modules.misc;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.models.Timer;
import me.nolikdevelopment.impossibleclient.util.player.ChatUtil;
import me.nolikdevelopment.impossibleclient.util.player.PlayerUtil;

// TODO: улучшить

public class Announcer extends Module {
    Timer timer = new Timer();
    public Announcer() {
        super("Announcer", "Writes messages in the chat", Category.MISC);
    }
    @Subscribe public void onTick() {
        if (timer.passedS(15)) {
                if (PlayerUtil.isGliding()) {
                    ChatUtil.sendChatMessage(String.format("Я лечу на элитрах с помощью %s!", Impossible.CLIENT_NAME));
                    timer.reset();
            }
        }
    }
}
