package me.nolikdevelopment.impossibleclient.features.modules.player;

import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.traits.Util;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class AutoTool extends Module implements Util {
    public AutoTool() {
        super("AutoTool", "Automatically selecting tool for use.", Category.PLAYER);
    }

    @Override
    public void onTick() {
        if (mc.options.keyAttack.isDown()) {
            BlockHitResult hitResult = (BlockHitResult) mc.hitResult;
            BlockState state = mc.level.getBlockState(hitResult.getBlockPos());

            int bestSlot = -1;
            int bestSpeed = 1;

            for (int i = 0; i < 9; i++) {
                int sp = (int) mc.player.getInventory().getItem(i).getDestroySpeed(state);

                if (sp > bestSpeed) {
                    bestSlot = i;
                    bestSpeed = sp;
                }
            }

            if (bestSlot != -1 && mc.player.getInventory().getSelectedSlot() != bestSlot) {
                mc.player.getInventory().setSelectedSlot(bestSlot);
            }
        }
    }
}
