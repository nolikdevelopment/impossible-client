package me.nolikdevelopment.impossibleclient.features.modules.movement;


import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.MathUtil;
import me.nolikdevelopment.impossibleclient.util.player.PlayerUtil;
import net.minecraft.world.entity.vehicle.Boat;


public class BoatFly extends Module {
    public BoatFly() {
        super("BoatFly", "Fly using boats", Category.MOVEMENT);
    }
    @Subscribe public void onTick() {
        if (PlayerUtil.getVehicle() instanceof Boat) {
            if (mc.options.keyJump.isDown()) {
                mc.player.setDeltaMovement(mc.player.getDeltaMovement().x, 1, mc.player.getDeltaMovement().z);
            } else if (mc.options.keyShift.isDown()) {
                mc.player.setDeltaMovement(mc.player.getDeltaMovement().x, -1, mc.player.getDeltaMovement().z);
            }
            double speed[] = MathUtil.directionSpeed(1);
            mc.player.setDeltaMovement(speed[0], mc.player.getDeltaMovement().y, speed[1]);
        }
    }
}