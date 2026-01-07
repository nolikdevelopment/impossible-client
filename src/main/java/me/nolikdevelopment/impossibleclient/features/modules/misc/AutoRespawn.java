package me.nolikdevelopment.impossibleclient.features.modules.misc;

import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.player.PlayerUtil;
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
