package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.Impossible;
import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;


public class FastFall extends Module {
    private boolean returnTimers = true;
    public FastFall() {
        super("FastFall", "", Category.MOVEMENT);
    }
    @Subscribe public void onTick() {
        if (checkPos()) {
            isFall();
        }
    }
    private boolean checkPos() {
        if (!mc.player.onGround()) return false;
        if (mc.player.fallDistance > 3) return false;
        if (mc.player.isFallFlying()) return false;
        if (mc.player.isDeadOrDying()) return false;
        if (mc.player.isInLiquid()) return false;
        if (mc.player.isMovingSlowly()) return false;
        return true;
    }
    private void isFall() {
        if (checkPos()) {
            Impossible.TIMER = 5f;
            mc.player.setDeltaMovement(mc.player.getDeltaMovement().x, -1, mc.player.getDeltaMovement().z);
            returnTimer();
        }
    }
    private void returnTimer() {
       if (returnTimers) {
           Impossible.TIMER = 1f;
       }
    }
}
