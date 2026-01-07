package me.nolikdevelopment.impossibleclient.features.commands;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.Feature;
import me.nolikdevelopment.impossibleclient.util.TextUtil;
import me.nolikdevelopment.impossibleclient.util.player.ChatUtil;

public abstract class Command
        extends Feature {
    protected String name;
    protected String[] commands;

    public Command(String name) {
        super(name);
        this.name = name;
        this.commands = new String[]{""};
    }

    public Command(String name, String[] commands) {
        super(name);
        this.name = name;
        this.commands = commands;
    }

    public static void sendMessage(String message, Object... obj) {
        ChatUtil.sendMessage(TextUtil.text(message, obj));
    }

    public static String getCommandPrefix() {
        return Impossible.commandManager.getPrefix();
    }

    public abstract void execute(String[] var1);

    @Override
    public String getName() {
        return this.name;
    }

    public String[] getCommands() {
        return this.commands;
    }
}