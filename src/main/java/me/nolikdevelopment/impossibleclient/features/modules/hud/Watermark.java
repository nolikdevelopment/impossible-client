package me.nolikdevelopment.impossibleclient.features.modules.hud;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.event.impl.Render2DEvent;
import me.nolikdevelopment.impossibleclient.features.modules.client.HudModule;
import me.nolikdevelopment.impossibleclient.features.settings.Setting;
import me.nolikdevelopment.impossibleclient.util.TextUtil;

public class Watermark extends HudModule {
    public Setting<String> text = str("Text", Impossible.NAME);

    public Watermark() {
        super("Watermark", "Display watermark", 100, 10);
    }

    @Override
    protected void render(Render2DEvent e) {
        super.render(e);

        e.getContext().drawString(mc.font,
                TextUtil.text(text.getValue(), ""),
                (int) getX(), (int) getY(), -1);

        String watermarkString = "" + Impossible.NAME;
        setWidth(mc.font.width(watermarkString));
        setHeight(mc.font.lineHeight);
    }
}

