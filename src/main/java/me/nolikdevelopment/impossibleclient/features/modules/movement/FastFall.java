package me.nolikdevelopment.impossibleclient.features.modules.movement;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.player.PlayerUtil;


public class FastFall extends Module {
    private boolean returnTimers = true;
    public FastFall() {
        super("FastFall", "Acceleration of player fall", Category.MOVEMENT);
    }
    @Subscribe public void onTick() {
        if (checkPos()) {
            isFall();
        }
    }
    private boolean checkPos() {
        if (!PlayerUtil.onGround()) return false;
        if (PlayerUtil.getFallDistance(3)) return false;
        if (PlayerUtil.isGliding()) return false;
        if (PlayerUtil.isDead()) return false;
        if (PlayerUtil.isInLiquid()) return false;
        if (PlayerUtil.isSneaking()) return false;
        return true;
    }
    private void isFall() {
        if (checkPos()) {
            Impossible.TIMER = 5f;
            mc.player.setDeltaMovement(mc.player.getDeltaMovement().x, -5, mc.player.getDeltaMovement().z);
            returnTimer();
        }
    }
    private void returnTimer() {
       if (returnTimers) {
           Impossible.TIMER = 1f;
       }
    }
}
