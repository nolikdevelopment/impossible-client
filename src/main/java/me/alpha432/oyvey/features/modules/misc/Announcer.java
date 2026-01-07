package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.Impossible;
import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.models.Timer;
import me.alpha432.oyvey.util.player.ChatUtil;
import me.alpha432.oyvey.util.player.PlayerUtil;

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
