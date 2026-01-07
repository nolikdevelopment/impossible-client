package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.event.impl.SlowEvent;
import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;

public class NoSlow extends Module {
    public NoSlow() {
        super("NoSlow", "Prevents braking on blocks", Category.MOVEMENT);
    }
    @Subscribe public void onTick(SlowEvent event) {
       event.cancel();
    }
}
