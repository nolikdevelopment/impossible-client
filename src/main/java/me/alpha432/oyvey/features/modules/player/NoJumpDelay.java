package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.mixin.IMixin.ILivingEntity;

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
