package me.nolikdevelopment.impossibleclient.features.modules.player;

import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.mixin.IMixin.ILivingEntity;

public class NoJumpDelay extends Module {
    public NoJumpDelay() {
        super("NoJumpDelay", "Removes the delay between jumps", Category.PLAYER);
    }
    @Subscribe public void onTick() {
        if (mc.options.keyJump.isDown()) {
            ((ILivingEntity) mc.player).setCooldown(0);
        }
    }
}
