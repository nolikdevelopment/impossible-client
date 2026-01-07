package me.nolikdevelopment.impossibleclient.features.modules.client;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.event.impl.TickEvent;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.gui.HudEditorScreen;
import me.nolikdevelopment.impossibleclient.features.modules.Module;

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

