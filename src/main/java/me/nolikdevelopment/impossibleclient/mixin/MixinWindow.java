package me.nolikdevelopment.impossibleclient.mixin;

import com.mojang.blaze3d.platform.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mixin(Window.class)
public class MixinWindow {
    @Inject(method = "close", at = @At("HEAD"))
    private void aVoid(CallbackInfo ci) {
        File file = new File("oyvey", "test");
        file.mkdirs();
        Path path = Paths.get("oyvey/test/" + "test.txt");
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}