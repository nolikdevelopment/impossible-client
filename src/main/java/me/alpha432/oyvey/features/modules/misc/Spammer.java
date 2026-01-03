package me.alpha432.oyvey.features.modules.misc;


import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Spammer extends Module {
    // TODO: улучшить читаемость, настройки
    private boolean created;
    private boolean isFile;

    public Spammer() {
        super("Spammer", "", Category.MISC);
    }

    @Subscribe
    public void onTick() {
        File folder = new File("oyvey","spammer");
        if (!folder.exists()) {
            created = folder.mkdir();
        }
        File file = new File(folder, "spammer.txt");
        try {
            if (!file.exists()) {
                isFile = file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                mc.getConnection().sendChat(line);
            }
        } catch (IOException e) {
        }
    }
}