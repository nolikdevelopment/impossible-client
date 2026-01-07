package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.world.effect.MobEffects;

public class LevitationControl extends Module {
    public LevitationControl() {
        super("LevitationControl", "Improved shulker levitation controls", Category.MOVEMENT);
    }
    @Subscribe public void onTick() {
        if (mc.player.hasEffect(MobEffects.LEVITATION)) {
            if (mc.options.keyJump.isDown()) {
                mc.player.setDeltaMovement(mc.player.getDeltaMovement().x, 1, mc.player.getDeltaMovement().z);
            } else if (mc.options.keyShift.isDown()) {
                mc.player.setDeltaMovement(mc.player.getDeltaMovement().x, -1, mc.player.getDeltaMovement().z);
            }
        }
    }
}
