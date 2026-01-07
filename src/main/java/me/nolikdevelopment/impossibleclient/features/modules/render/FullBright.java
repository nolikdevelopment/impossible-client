package me.nolikdevelopment.impossibleclient.features.modules.render;

import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class FullBright extends Module {
    public FullBright() {
        super("FullBright", "Provides maximum illumination", Category.RENDER);
    }
    @Subscribe public void onTick() {
        if (!mc.player.hasEffect(MobEffects.NIGHT_VISION)) {
            mc.player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 999999999));
        }
    }
}
