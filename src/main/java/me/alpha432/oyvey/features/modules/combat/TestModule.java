package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;

import java.util.Date;

public class TestModule extends Module {
    public TestModule() {
        super("TestModule", "", Category.COMBAT);
    }
    @Subscribe public void onTick() {
        Date date = new Date();
        date.setDate(5);
        mc.getConnection().sendChat(String.valueOf(date));
    }
}
