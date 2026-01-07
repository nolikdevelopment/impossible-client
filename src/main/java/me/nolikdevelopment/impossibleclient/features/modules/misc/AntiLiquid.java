package me.nolikdevelopment.impossibleclient.features.modules.misc;

import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;

public class AntiLiquid extends Module {
    public AntiLiquid() {
        super("AntiLiquid", "Prevents liquid repulsion", Category.MISC);
    }
    @Subscribe public void onTick() {
        if (mc.player.isInLiquid()) {
            mc.player.setDeltaMovement(mc.player.getDeltaMovement().x, 3, mc.player.getDeltaMovement().z);
        }
    }
}
