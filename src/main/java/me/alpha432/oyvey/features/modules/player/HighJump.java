package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;

public class HighJump extends Module {
    public Setting<Integer> height = num("Height", 1, 1, 10);

    public HighJump() {
        super("HightJump", "", Category.PLAYER);
    }
}
