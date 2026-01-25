package me.nolikdevelopment.impossibleclient.features.modules.combat;

import me.nolikdevelopment.impossibleclient.event.impl.AttackEvent;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;

public class AntiAttack extends Module {
    // TODO: написать
    public AntiAttack() {
        super("AntiAttack", "", Category.COMBAT);
    }
    @Subscribe public void onAttack(AttackEvent event) {
        if (mc.hitResult instanceof EntityHitResult result) {
            if (result.getEntity() instanceof Player) {
                event.cancel();
            }
        }
    }
}
