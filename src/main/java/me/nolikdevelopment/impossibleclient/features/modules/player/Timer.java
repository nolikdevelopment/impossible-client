package me.nolikdevelopment.impossibleclient.features.modules.player;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.features.settings.Setting;

public class Timer extends Module {
    public Setting<Float> multi = num("Multi:", 1.3f, 0.1f, 5.0f);
    public Timer() {
        super("Timer", "Game should be quickly!!!", Category.PLAYER);
    }
    @Subscribe public void onTick() {
        Impossible.TIMER = multi.getValue();
    }
    @Subscribe public void onDisable() {
        Impossible.TIMER = 1;
    }
}