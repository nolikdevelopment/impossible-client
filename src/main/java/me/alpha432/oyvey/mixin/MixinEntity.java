package me.alpha432.oyvey.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import me.alpha432.oyvey.Impossible;
import me.alpha432.oyvey.features.modules.misc.AntiLiquid;
import me.alpha432.oyvey.features.modules.movement.NoSlow;
import me.alpha432.oyvey.features.modules.player.HighJump;
import me.alpha432.oyvey.features.modules.render.NoRender;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class MixinEntity {
    @ModifyReturnValue(method = "getBlockJumpFactor", at = @At("RETURN"))
    private float aVoid(float original) {
        HighJump hightJump = Impossible.moduleManager.getModuleByClass(HighJump.class);
        if (hightJump.isEnabled()) {
            return original + hightJump.height.getValue();
        }
        return original;
    }

    @Inject(method = "waterSwimSound", at = @At("HEAD"), cancellable = true)
    private void aVoid(CallbackInfo ci) {
        AntiLiquid antiLiquid = Impossible.moduleManager.getModuleByClass(AntiLiquid.class);
        if (antiLiquid.isEnabled()) {
            ci.cancel();
        }
    }

    @Inject(method = "isOnFire", at = @At("HEAD"), cancellable = true)
    private void aVoid1(CallbackInfoReturnable<Boolean> cir) {
        NoRender noRender = Impossible.moduleManager.getModuleByClass(NoRender.class);
        if (noRender.isEnabled()) {
            cir.setReturnValue(false);
        }
    }

    @ModifyExpressionValue(method = "getBlockSpeedFactor", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getBlock()Lnet/minecraft/world/level/block/Block;"))
    private Block aVoid(Block original) {
        NoSlow noSlow = Impossible.moduleManager.getModuleByClass(NoSlow.class);
        if (noSlow.isEnabled()) {
            if (original == Blocks.SOUL_SAND || original == Blocks.HONEY_BLOCK) {
                return Blocks.BEDROCK;
            }
        }
        return original;
    }
}