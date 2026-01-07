package me.nolikdevelopment.impossibleclient.features.modules.movement;

import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.player.PlayerUtil;

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
