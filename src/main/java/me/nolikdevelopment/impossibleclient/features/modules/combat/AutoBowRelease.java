package me.nolikdevelopment.impossibleclient.features.modules.combat;

import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.player.PlayerUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;

public class AutoBowRelease extends Module {
    public AutoBowRelease() {
        super("AutoBowRelease", "", Category.COMBAT);
    }
    @Subscribe public void onTick() {
        if (mc.player.getTicksUsingItem() >= 5) {
            if (PlayerUtil.getWeaponOffhand().getItem() == Items.BOW || PlayerUtil.isUsingItem()) {
               mc.getConnection().send(new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.Action.RELEASE_USE_ITEM, BlockPos.ZERO, mc.player.getMotionDirection()));
               mc.getConnection().send(new ServerboundUseItemPacket(PlayerUtil.getWeaponOffhand().getItem() == Items.BOW ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND, 0, mc.player.getXRot(), mc.player.getYRot()));
               mc.player.stopUsingItem();
            }
        }
    }
}
