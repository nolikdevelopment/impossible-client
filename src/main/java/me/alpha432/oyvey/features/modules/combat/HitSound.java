package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.event.impl.HitEvent;
import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;

public class HitSound extends Module {
    public HitSound() {
        super("HitSound", "Sets the hit sound.", Category.COMBAT);
    }
    @Subscribe public void onHit(HitEvent event) {
       if (event.getPlayer() == mc.player) {
           if (event.getEntity() instanceof EndCrystal) return;
           mc.player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1, mc.player.getXRot());
       }
    }
}
