package me.nolikdevelopment.impossibleclient.features.modules.movement;

import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.MathUtil;
import net.minecraft.world.phys.Vec3;

public class Flight extends Module {
    public Flight() {
        super("Flight", "Flight...", Category.MOVEMENT);
    }

    @Subscribe
    public void onTick() {
        if (mc.options.keyJump.isDown()) {
            mc.player.setDeltaMovement(new Vec3(0, 1, 0));
        } else if (mc.options.keyShift.isDown()) {
            mc.player.setDeltaMovement(new Vec3(0, -1, 0));
        } else {
            mc.player.setDeltaMovement(new Vec3(0, -0, 0));
        }
        double speed[] = MathUtil.directionSpeed(1);
        mc.player.setDeltaMovement(new Vec3(speed[0], mc.player.getDeltaMovement().y, speed[1]));
    }
}

