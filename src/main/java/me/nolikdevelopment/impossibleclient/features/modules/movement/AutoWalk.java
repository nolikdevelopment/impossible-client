package me.nolikdevelopment.impossibleclient.features.modules.movement;

import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;


public class AutoWalk extends Module {
    public AutoWalk() {
        super("AutoWalk", "Automatic walking", Category.MOVEMENT);
    }
    @Subscribe public void onTick() {
        if (!mc.options.keyUp.isDown()) {
            mc.options.keyUp.setDown(true);
        }
    }
    @Subscribe public void onDisable() {
        mc.options.keyUp.setDown(false);
    }
}
