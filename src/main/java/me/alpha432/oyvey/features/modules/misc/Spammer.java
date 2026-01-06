package me.alpha432.oyvey.features.modules.misc;


import me.alpha432.oyvey.Impossible;
import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.commands.Command;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Spammer extends Module {
    public Setting<Integer> delayPerTick = num("Delay", 20, 1, 500);
    public Setting<Boolean> isFile = bool("IsFile", false);
    public Setting<String> text = str("Text", Impossible.CLIENT_NAME + " ON TOP!");//default
    public Setting<Boolean> antiSpam = bool("AntiSpam", false);

    private int timer = 0;
    private String messageToSend = "";

    public Spammer() {
        super("Spammer", "", Category.MISC);
    }

    @Override
    public void onEnable() {
        timer = 0;
        prepareMessage();
    }

    @Override
    public void onDisable() {
        timer = 0;
        messageToSend = "";
    }

    @Subscribe
    public void onTick() {
        if (messageToSend == null || messageToSend.isEmpty()) {
            return;
        }

        timer++;

        if (timer >= delayPerTick.getValue()) {
            timer = 0;
            String msg = messageToSend;

            if (antiSpam.getValue()) {
                msg += " | " + StringUtils.generateRandomString(4);
            }


            mc.getConnection().sendChat(msg.toString());
        }
    }

    private void prepareMessage() {
        if (!isFile.getValue()) {
            this.messageToSend = text.getValue();
            Command.sendMessage("{green}Spammer успешно включен в режиме текста");
            return;
        }

        try {
            File folder = new File("oyvey", "spammer");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            Path path = Paths.get("oyvey/spammer/" + text.getValue());
            if (!Files.exists(path)) {
                Command.sendMessage("{red}Файл " + path.toString() + " не найден!");
                disable();
                return;
            }

            String content = Files.readString(path);

            if (content.isEmpty()) {
                Command.sendMessage("{yellow}В файле " + path.toString() + " нету текста!");
                disable();
                return;
            }

            messageToSend = content;
            Command.sendMessage("{green}Spammer включен в режиме файла.");
        } catch (IOException e) {
            Command.sendMessage("{red}Сторонняя ошибка: " + e.getMessage());//Не должно, но пусть будет
            e.printStackTrace();
            disable();
        }
    }
}