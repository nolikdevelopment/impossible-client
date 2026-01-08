package me.nolikdevelopment.impossibleclient.mixin;

import me.nolikdevelopment.impossibleclient.event.impl.ChatEvent;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.world.item.crafting.RecipeAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.nolikdevelopment.impossibleclient.util.traits.Util.EVENT_BUS;

@Mixin(ClientPacketListener.class)
public abstract class MixinClientPlayNetworkHandler {
    @Shadow
    public abstract void sendChat(String string);

    @Inject(method = "sendChat", at = @At("HEAD"), cancellable = true)
    private void sendChatMessageHook(String content, CallbackInfo ci) {
        ChatEvent event = new ChatEvent(content);
        EVENT_BUS.post(event);

        if (event.isCancelled()) {
            ci.cancel();
            return;
        }

        if (!event.getMessage().equals(content)) {
            ci.cancel();
            this.sendChat(event.getMessage());
        }
    }
}