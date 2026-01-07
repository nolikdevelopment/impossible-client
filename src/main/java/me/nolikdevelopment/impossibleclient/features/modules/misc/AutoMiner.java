package me.nolikdevelopment.impossibleclient.features.modules.misc;

import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.NetherrackBlock;
import net.minecraft.world.phys.BlockHitResult;

public class AutoMiner extends Module {
    public AutoMiner() {
        super("AutoMiner", "", Category.MISC);
    }
    @Subscribe public void onTick() {
        if (mc.hitResult instanceof BlockHitResult result) {
            if (mc.player.getMainHandItem().getItem() == Items.NETHERITE_PICKAXE) {
                if (mc.level.getBlockState(result.getBlockPos()).getBlock() instanceof NetherrackBlock) {
                    BlockPos pos = result.getBlockPos();
                    Direction direction = result.getDirection();
                    mc.gameMode.startDestroyBlock(pos, direction);
                    mc.player.swing(InteractionHand.MAIN_HAND);
                }
            }
        }
    }
}
