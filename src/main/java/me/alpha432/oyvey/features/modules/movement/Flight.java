package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.MathUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.fog.FogRenderer;

public class Flight extends Module {
    public Flight() {
        super("Flight", "Flight...", Category.MOVEMENT);
    }

    @Subscribe
    public void onTick() {
        if (mc.options.keyJump.isDown()) {
            mc.player.setDeltaMovement(mc.player.getDeltaMovement().x,1, mc.player.getDeltaMovement().z);
        } else if (mc.options.keyShift.isDown()) {
            mc.player.setDeltaMovement(mc.player.getDeltaMovement().x, -1, mc.player.getDeltaMovement().z);
        } else {
            mc.player.setDeltaMovement(mc.player.getDeltaMovement().x, -1, mc.player.getDeltaMovement().z);
        }
        double speed[] = MathUtil.directionSpeed(3);
        mc.player.setDeltaMovement(speed[0], mc.player.getDeltaMovement().y, speed[1]);
    }
}

