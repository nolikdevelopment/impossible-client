package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;

public class CoordinateLogger extends Module {
    public CoordinateLogger() {
        super("CoordinateLogger", "Stores death coordinates", Category.MISC);
    }
    @Subscribe public void onTick() {
        if (mc.player.isDeadOrDying()) {
            String coordinates = "X: " + (int) mc.player.getX() + " Y: " + (int) mc.player.getY() + " Z: " + (int) mc.player.getZ();
            mc.keyboardHandler.setClipboard(coordinates);
        }
    }
}
