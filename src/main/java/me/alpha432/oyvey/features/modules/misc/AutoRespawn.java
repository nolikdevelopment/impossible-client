package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.player.PlayerUtil;
import net.minecraft.client.gui.screens.DeathScreen;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespawn", "Automatic respawn", Category.MISC);
    }
    @Subscribe public void onTick() {
            if (PlayerUtil.isDead()) {
            if (mc.screen instanceof DeathScreen) {
                mc.player.respawn();
            }
        }
    }
}
