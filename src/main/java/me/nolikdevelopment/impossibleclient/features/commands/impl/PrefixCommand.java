package me.nolikdevelopment.impossibleclient.features.commands.impl;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.commands.Command;

public class PrefixCommand
        extends Command {
    public PrefixCommand() {
        super("prefix", new String[]{"<char>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            Command.sendMessage("{green} Current prefix is %s ", Impossible.commandManager.getPrefix());
            return;
        }
        Impossible.commandManager.setPrefix(commands[0]);
        Command.sendMessage("Prefix changed to {gray} %s", commands[0]);
    }
}