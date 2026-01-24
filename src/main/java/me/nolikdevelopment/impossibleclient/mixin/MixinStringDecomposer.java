package me.nolikdevelopment.impossibleclient.mixin;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.modules.player.NameProtect;
import me.nolikdevelopment.impossibleclient.util.traits.Util;
import net.minecraft.util.StringDecomposer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(StringDecomposer.class)
public class MixinStringDecomposer implements Util {
    @ModifyVariable(method = "iterateFormatted(Ljava/lang/String;Lnet/minecraft/network/chat/Style;Lnet/minecraft/util/FormattedCharSink;)Z", at = @At("HEAD"))
    private static String aVoid(String value) {
        NameProtect nameProtect = Impossible.moduleManager.getModuleByClass(NameProtect.class);
        if (nameProtect.isEnabled()) {
            return value.replaceAll(mc.getUser().getName(), nameProtect.name.getValue());
        }
        return value;
    }
}
