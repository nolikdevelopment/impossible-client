package me.nolikdevelopment.impossibleclient.features.modules.misc;

import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.features.settings.Setting;
import me.nolikdevelopment.impossibleclient.util.models.Timer;
import me.nolikdevelopment.impossibleclient.util.traits.Util;

import java.util.Random;

public class AntiAFK extends Module implements Util {
    public Setting<Boolean> sneaking = bool("Sneaking", true);
    public Setting<Boolean> moving = bool("Moving", true);
    public Setting<Boolean> jumping = bool("Jumping", true);
    public Setting<Float> delay = num("Delay", 1f, 0.5f, 60f);//IN SECONDS!!!

    private Timer timer = new Timer();

    public AntiAFK() {
        super("AntiAFK", "", Category.MISC);
    }

    @Override
    public void enable() {
        timer.reset();
    }

    @Override
    public void disable() {
        timer.reset();
    }

    @Override
    public void onTick() {
        if (mc.player == null) return;

        if (timer.passedS(delay.getValue())){
            Random rnd = new Random();

            if (rnd.nextInt(100) > 30 && moving.getValue()) {//70% chance to move
                int where = rnd.nextInt(6);
                if (where == 0) {
                    mc.player.setDeltaMovement(1, 0, 0);
                } else if (where == 1) {
                    mc.player.setDeltaMovement(0, 0, 1);
                } else if (where == 2) {
                    mc.player.setDeltaMovement(1, 0, 1);
                } else if (where == 3) {
                    mc.player.setDeltaMovement(-1, 0, 0);
                } else if (where == 4) {
                    mc.player.setDeltaMovement(0, 0, -1);
                } else {
                    mc.player.setDeltaMovement(-1, 0, -1);
                }
            }

            if (rnd.nextInt(100) > 80 && sneaking.getValue()) {//20% chance to sneak
                mc.player.setJumping(true);
            }

            if (rnd.nextInt(100) > 90 && jumping.getValue()) {//10$ chance to jump
                mc.options.keyJump.setDown(true);
            }
            timer.reset();
        }
    }
}
