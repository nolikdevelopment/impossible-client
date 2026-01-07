package me.nolikdevelopment.impossibleclient.features.modules.player;

import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.player.PlayerUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.EntityHitResult;

public class AutoNameTag extends Module {
    public AutoNameTag() {
        super("AutoNameTag", "", Category.PLAYER);
    }
    @Subscribe public void onTick() {
        for (Entity entity : mc.level.entitiesForRendering()) {
            if (PlayerUtil.getWeaponOffhand().getItem() == Items.NAME_TAG) {
                if (entity.position().distanceTo(mc.player.position()) > 6) continue;
                if (entity == null) continue;
                if (entity.getCustomName() != null) continue;
                if (entity instanceof EnderDragon || entity instanceof Villager) continue;
                isTagged(entity);
            }
        }
    }
    private void isTagged(Entity entity) {
        if (mc.hitResult instanceof EntityHitResult entityHitResult) {
            if (entityHitResult.getEntity() == entity) {
                mc.gameMode.interactAt(mc.player, entity, entityHitResult, InteractionHand.MAIN_HAND);
                mc.gameMode.interact(mc.player, entity, InteractionHand.MAIN_HAND); // ебать рот тут 2 раз надо писать
                mc.player.swing(InteractionHand.MAIN_HAND);
            }
        }
    }
}
