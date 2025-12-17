package me.alpha432.oyvey.features.modules.hud;

import me.alpha432.oyvey.Impossible;
import me.alpha432.oyvey.event.impl.Render2DEvent;
import me.alpha432.oyvey.features.modules.client.HudModule;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.TextUtil;

public class Watermark extends HudModule {
    public Setting<String> text = str("Text", Impossible.NAME);

    public Watermark() {
        super("Watermark", "Display watermark", 100, 10);
    }

    @Override
    protected void render(Render2DEvent e) {
        super.render(e);

        e.getContext().drawString(mc.font,
                TextUtil.text("{global} %s {} %s", text.getValue()),
                (int) getX(), (int) getY(), -1);

        String watermarkString = text.getValue() + "";
        setWidth(mc.font.width(watermarkString));
        setHeight(mc.font.lineHeight);
    }
}

