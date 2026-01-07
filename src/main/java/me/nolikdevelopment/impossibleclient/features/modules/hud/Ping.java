package me.nolikdevelopment.impossibleclient.features.modules.hud;


import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.event.impl.Render2DEvent;
import me.nolikdevelopment.impossibleclient.features.modules.client.HudModule;

public class Ping extends HudModule {
    public Ping() {
        super("Ping", "Show player ping", 20, 20);
    }
    @Override
    protected void render(Render2DEvent event) {
        super.render(event);
        if (nullCheck()) return;
        String str = "Ping " + Impossible.serverManager.getPing();
        event.getContext().drawString(mc.font, str, (int) getX(), (int) getY(), -1);
        setWidth(mc.font.width(str));
        setHeight(mc.font.lineHeight);
    }
}
