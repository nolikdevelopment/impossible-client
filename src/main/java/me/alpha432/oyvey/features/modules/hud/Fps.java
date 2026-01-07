package me.alpha432.oyvey.features.modules.hud;

import me.alpha432.oyvey.event.impl.Render2DEvent;
import me.alpha432.oyvey.features.modules.client.HudModule;

public class Fps extends HudModule {

    public Fps() {
        super("Fps", "Display FPS", 20, 20);
    }

    @Override
    protected void render(Render2DEvent e) {
        super.render(e);

        if (nullCheck()) return;

        int fps = mc.getFps();

        String str = "FPS: " + fps;

        e.getContext().drawString(mc.font, str, (int) getX(), (int) getY(), -1);

        setWidth(mc.font.width(str));
        setHeight(mc.font.lineHeight);
    }
}
