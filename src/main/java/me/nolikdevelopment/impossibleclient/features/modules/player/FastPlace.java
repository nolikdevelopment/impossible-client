package me.nolikdevelopment.impossibleclient.features.modules.player;

import me.nolikdevelopment.impossibleclient.features.modules.Module;
import net.minecraft.world.item.Items;

public class FastPlace extends Module {
    public FastPlace() {
        super("FastPlace", "Makes you throw exp faster", Category.PLAYER);
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;

        if (mc.player.isHolding(Items.EXPERIENCE_BOTTLE)) {
            mc.rightClickDelay = 0;
        }
    }
}
