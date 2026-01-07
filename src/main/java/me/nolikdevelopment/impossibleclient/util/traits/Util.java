package me.nolikdevelopment.impossibleclient.util.traits;

import me.nolikdevelopment.impossibleclient.event.system.EventBus;
import net.minecraft.client.Minecraft;

public interface Util {
    Minecraft mc = Minecraft.getInstance();
    EventBus EVENT_BUS = new EventBus();
}
