package me.nolikdevelopment.impossibleclient.util.player;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.commands.Command;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import static me.nolikdevelopment.impossibleclient.util.traits.Util.mc;

public class ChatUtil {

    public static void sendMessage(Component message) {
        MutableComponent text = Component.empty();
        text.append(Impossible.commandManager.getClientMessage()+" ");
        text.append(message);
        sendSilentMessage(text);
    }

    public static void sendSilentMessage(Component message) {
        if (Command.nullCheck()) {
            return;
        }
        // TODO add silent support ig
        mc.gui.getChat().addMessage(message);
    }
    public static void sendChatMessage(String message) {
        if (mc.getConnection().getConnection() != null) {
            mc.getConnection().sendChat(message);
        }
    }
}