package me.nolikdevelopment.impossibleclient.features.modules.player;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.player.InventoryUtil;
import me.nolikdevelopment.impossibleclient.util.player.PlayerUtil;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class Scaffold extends Module {
    public Scaffold() {
        super("Scaffold", "", Category.PLAYER);
    }
    @Subscribe public void onTick() {
        if (PlayerUtil.onGround() || !PlayerUtil.isUsingItem() || !PlayerUtil.isGliding()) {
                LocalPlayer player = mc.player;
                BlockPos pos = player.blockPosition().offset(new BlockPos(0, -1, 0));
                mc.player.swing(InteractionHand.MAIN_HAND);
                Impossible.rotationManager.rotateToBlockPos(pos);
                mc.gameMode.useItemOn(player, InteractionHand.MAIN_HAND, new BlockHitResult(Vec3.atCenterOf(pos), Direction.DOWN, pos, false));
        }
    }
}
