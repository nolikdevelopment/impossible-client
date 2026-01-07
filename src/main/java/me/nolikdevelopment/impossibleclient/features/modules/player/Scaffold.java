package me.nolikdevelopment.impossibleclient.features.modules.player;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.InteractionUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;

public class Scaffold extends Module {
    public Scaffold() {
        super("Scaffold", "Puts blocks under you", Category.PLAYER);
    }
    @Subscribe public void onTick() {
        BlockPos pos = mc.player.blockPosition().below();
        Impossible.rotationManager.rotateToBlockPos(pos);
        mc.player.swing(InteractionHand.MAIN_HAND);
        InteractionUtil.place(pos, true, InteractionHand.MAIN_HAND);
    }
}
