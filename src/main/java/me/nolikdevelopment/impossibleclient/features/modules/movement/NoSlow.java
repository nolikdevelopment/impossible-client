package me.nolikdevelopment.impossibleclient.features.modules.movement;

import me.nolikdevelopment.impossibleclient.event.impl.SlowEvent;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;

public class NoSlow extends Module {
    public NoSlow() {
        super("NoSlow", "Prevents braking on blocks", Category.MOVEMENT);
    }
    @Subscribe public void onTick(SlowEvent event) {
       event.cancel();
    }
}
