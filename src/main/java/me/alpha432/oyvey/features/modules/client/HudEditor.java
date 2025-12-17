package me.alpha432.oyvey.features.modules.client;

import me.alpha432.oyvey.Impossible;
import me.alpha432.oyvey.event.impl.TickEvent;
import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.gui.HudEditorScreen;
import me.alpha432.oyvey.features.modules.Module;

public class HudEditor extends Module {
    public HudEditor() {
        super("HudEditor", "Edit HUD element positions", Category.CLIENT);
    }

    @Override
    public void onEnable() {
        if (nullCheck()) {
            disable();
            return;
        }
        mc.setScreen(Impossible.hudEditorScreen);
    }

    @Override
    public void onDisable() {
        if (nullCheck()) return;
        mc.setScreen(null);
    }

    @Subscribe
    public void onTick(TickEvent event) {
        if (nullCheck()) return;
        if (!(mc.screen instanceof HudEditorScreen)) {
            disable();
        }
    }
}

