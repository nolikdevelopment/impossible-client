package me.nolikdevelopment.impossibleclient.features.modules.combat;

import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.traits.Util;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;

public class TriggetBot extends Module implements Util {
    public TriggetBot() {
        super("TriggerBot", "Simple KillAura :)", Category.COMBAT);
    }

    @Override
    public void onTick() {
        Entity target = mc.crosshairPickEntity;

        if (target == null || !target.isAlive()) return;

        boolean isCooldownReady = mc.player.getAttackStrengthScale(0.5f) >= 1.0f;

        if (isCooldownReady) {
            mc.gameMode.attack(mc.player, target);
            mc.player.swing(InteractionHand.MAIN_HAND);
        }
    }
}
