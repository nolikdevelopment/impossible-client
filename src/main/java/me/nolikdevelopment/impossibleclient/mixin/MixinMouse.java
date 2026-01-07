package me.nolikdevelopment.impossibleclient.mixin;

import me.nolikdevelopment.impossibleclient.event.impl.MouseEvent;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.input.MouseButtonInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.nolikdevelopment.impossibleclient.util.traits.Util.EVENT_BUS;

@Mixin(MouseHandler.class)
public class MixinMouse {
    @Inject(method = "onButton", at = @At("HEAD"), cancellable = true)
    private void onMouseButton(long window, MouseButtonInfo input, int action, CallbackInfo ci) {
        MouseEvent event = new MouseEvent(input.button(), action);
        EVENT_BUS.post(event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }
}

