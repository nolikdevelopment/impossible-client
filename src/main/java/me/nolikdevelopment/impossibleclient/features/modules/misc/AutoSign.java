package me.nolikdevelopment.impossibleclient.features.modules.misc;

import me.nolikdevelopment.impossibleclient.event.impl.PacketEvent;
import me.nolikdevelopment.impossibleclient.event.impl.ScreenEvent;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.mixin.IMixin.IAbstractSignEditScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.minecraft.network.protocol.game.ServerboundSignUpdatePacket;
import net.minecraft.world.level.block.entity.SignBlockEntity;

public class AutoSign extends Module {
    private String[] strings;

    public AutoSign() {
        super("AutoSign", "Automatically signs signs", Category.MISC);
    }

    @Subscribe
    public void onPacket(PacketEvent.Send event) {
        if (event.getPacket() instanceof ServerboundSignUpdatePacket packet) {
            strings = packet.getLines();
        }
    }

    @Subscribe
    public void onScreen(ScreenEvent event) {
        // TODO: пиздец код грязь
        Screen screen = event.getScreen();
        if (screen == null) return;
        if (!(screen instanceof AbstractSignEditScreen) || strings == null) return;
        SignBlockEntity signBlock = ((IAbstractSignEditScreen) screen).getSign();
        if (signBlock == null) return;
        mc.getConnection().send(new ServerboundSignUpdatePacket(signBlock.getBlockPos(), true, "1", "1", "1", "1"));
        event.cancel();
    }
}
