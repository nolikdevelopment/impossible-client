package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;

public class AutoWalk extends Module {
    public AutoWalk() {
        super("AutoWalk", "", Category.MOVEMENT);
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
