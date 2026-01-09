package me.nolikdevelopment.impossibleclient.features.modules.player;

import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.traits.Util;

public class FastUse extends Module implements Util {
    public FastUse() {
        super("FastPlace", "", Category.PLAYER);
    }

    @Override
    public void onTick() {
        if (mc.player == null) return;

        mc.rightClickDelay = 0;
    }
}
