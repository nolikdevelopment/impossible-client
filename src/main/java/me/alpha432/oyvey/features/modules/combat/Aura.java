package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.Impossible;
import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.models.Timer;
import me.alpha432.oyvey.util.player.PlayerUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;

public class Aura extends Module {
    private Timer timer = new Timer();
    public Aura() {
        super("Aura", "Kill aura", Category.COMBAT);
    }
    @Subscribe public void onTick() {
        for (Entity entity : mc.level.entitiesForRendering()) {
            if (entity == mc.player) continue;
            if (entity == null) continue;
            if (entity.position().distanceTo(mc.player.position()) > 6) continue;
            if (entity.isInvisible()) continue;
            if (!entity.isAlive()) continue;
            if (isTargets(entity)) {
                isAttack(entity);
            }
        }
    }
    private boolean isTargets(Entity entity) {
        if (entity instanceof Player) return true;
        if (entity instanceof Monster) return true;
        if (entity instanceof Animal) return true;
        return false;
    }
    private void isAttack(Entity entity) {
        if (timer.passedS(0.87)) {
            mc.gameMode.attack(mc.player, entity);
            Impossible.rotationManager.rotateToEntity(entity);
            mc.player.swing(InteractionHand.MAIN_HAND);
            timer.reset();
        }
    }
}
