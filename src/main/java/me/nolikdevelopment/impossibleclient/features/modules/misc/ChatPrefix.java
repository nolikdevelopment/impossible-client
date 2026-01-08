package me.nolikdevelopment.impossibleclient.features.modules.misc;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.event.impl.ChatEvent;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.features.settings.Setting;
import me.nolikdevelopment.impossibleclient.util.traits.Util;

public class ChatPrefix extends Module implements Util {
    public Setting<String> text = str("Text", Impossible.CLIENT_NAME + " ON TOP!");

    public ChatPrefix() {
        super("CharPrefix", "Adding prefix for every message from you.", Category.MISC);
    }

    @Subscribe
    public void onChat(ChatEvent event) {
        String msg = event.getMessage();

        if (msg.startsWith("/") || msg.startsWith(Impossible.commandManager.getPrefix())) return;

        if (msg.endsWith(text.getValue())) return;

        event.setContent(msg + text.getValue());
    }
}
