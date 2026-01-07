package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;

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
