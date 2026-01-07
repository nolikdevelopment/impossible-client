package me.nolikdevelopment.impossibleclient.features.commands.impl;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.commands.Command;
import net.minecraft.ChatFormatting;

public class HelpCommand
        extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute(String[] commands) {
        HelpCommand.sendMessage("Commands: ");
        for (Command command : Impossible.commandManager.getCommands()) {
            StringBuilder builder = new StringBuilder(ChatFormatting.GRAY.toString());
            builder.append(Impossible.commandManager.getPrefix());
            builder.append(command.getName());
            builder.append(" ");
            for (String cmd : command.getCommands()) {
                builder.append(cmd);
                builder.append(" ");
            }
            HelpCommand.sendMessage(builder.toString());
        }
    }
}