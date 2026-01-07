package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.player.PlayerUtil;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Automatic sprint", Category.MOVEMENT);
    }
    @Subscribe public void onTick() {
        if (!PlayerUtil.isSprinting() || !PlayerUtil.isUsingItem() || !PlayerUtil.isSneaking()) {
            PlayerUtil.setSprinting();
        }
    }
}
