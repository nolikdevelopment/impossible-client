package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.Impossible;
import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.InteractionUtil;
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
