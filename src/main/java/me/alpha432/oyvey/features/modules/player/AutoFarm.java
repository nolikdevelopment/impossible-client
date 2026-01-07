package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.CarrotBlock;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.phys.BlockHitResult;

public class AutoFarm extends Module {
    public Setting<Boolean> place = bool("Place", false);
    public Setting<Boolean> break1 = bool("Break", true);
    public Setting<Boolean> gryaka = bool("BreakGryadka", false);

    public AutoFarm() {
        super("AutoFarm", "Automatic planting", Category.PLAYER);
    }

    @Subscribe
    public void onTick() {
        if (break1.getValue()) {
            if (mc.hitResult instanceof BlockHitResult hitResult) {
                if (mc.level.getBlockState(hitResult.getBlockPos()).getBlock() instanceof CarrotBlock) {
                    BlockPos pos = hitResult.getBlockPos();
                    Direction direction = hitResult.getDirection();
                    mc.gameMode.continueDestroyBlock(pos, direction);
                    mc.player.swing(InteractionHand.MAIN_HAND);
                }
            }
        }
        if (place.getValue()) {
            if (mc.hitResult instanceof BlockHitResult result) {
                if (mc.player.getMainHandItem().getItem() == Items.CARROT) {
                    if (mc.level.getBlockState(result.getBlockPos()).getBlock() instanceof FarmBlock) {
                        mc.gameMode.useItemOn(mc.player, InteractionHand.MAIN_HAND, result);
                        mc.player.swing(InteractionHand.MAIN_HAND);
                    }
                }
            }
        }
        if (gryaka.getValue()) {
            if (mc.hitResult instanceof BlockHitResult result1) {
                if (mc.player.getMainHandItem().getItem() == Items.STONE_HOE) {
                    if (mc.level.getBlockState(result1.getBlockPos()).getBlock() instanceof DirtPathBlock) {
                        mc.gameMode.useItemOn(mc.player, InteractionHand.MAIN_HAND, result1);
                        mc.player.swing(InteractionHand.MAIN_HAND);
                    }
                }
            }
        }
    }
}