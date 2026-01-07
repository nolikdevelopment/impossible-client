package me.nolikdevelopment.impossibleclient.features.modules.player;

import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.features.settings.Setting;

public class HighJump extends Module {
    public Setting<Integer> height = num("Height", 1, 1, 10);

    public HighJump() {
        super("HightJump", "", Category.PLAYER);
    }
}
