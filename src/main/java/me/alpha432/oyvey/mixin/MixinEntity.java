package me.alpha432.oyvey.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import me.alpha432.oyvey.Impossible;
import me.alpha432.oyvey.features.modules.player.HightJump;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public class MixinEntity {
    @ModifyReturnValue(method = "getBlockJumpFactor", at = @At("RETURN"))
    private float aVoid(float original) {
        HightJump hightJump = Impossible.moduleManager.getModuleByClass(HightJump.class);
        if (hightJump.isEnabled()) {
            return original + 1;
        }
        return original;
    }
}
