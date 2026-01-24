package me.nolikdevelopment.impossibleclient.features.modules.combat;

import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.player.InventoryUtil;
import me.nolikdevelopment.impossibleclient.util.player.PlayerUtil;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.Items;

public class TestModule extends Module {
    public TestModule() {
        super("TestModule", "", Category.COMBAT);
    }
    @Subscribe public void onTick() {

    }
}
