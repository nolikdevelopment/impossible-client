package me.nolikdevelopment.impossibleclient.features.modules.player;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.features.settings.Setting;

public class Timer extends Module {
    public Setting<Float> timer = num("Multiplier", 1f, 0.1f, 10f);

    public Timer() {
        super("Timer", "Game should be quickly!!!", Category.PLAYER);
    }

    @Override
    public void onTick() {
        Impossible.TIMER = timer.getValue();
    }

    @Override
    public void onDisable() {
        Impossible.TIMER = 1.0f;
    }
}
