package me.nolikdevelopment.impossibleclient.manager;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.event.Stage;
import me.nolikdevelopment.impossibleclient.event.impl.*;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.Feature;
import me.nolikdevelopment.impossibleclient.features.commands.Command;
import net.minecraft.ChatFormatting;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.BrandPayload;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.game.ClientboundSetTimePacket;
import net.minecraft.world.entity.player.Player;

public class EventManager extends Feature {
    public void init() {
        EVENT_BUS.register(this);
    }

    public void onUnload() {
        EVENT_BUS.unregister(this);
    }

    @Subscribe
    public void onTick(TickEvent event) {
        if (nullCheck())
            return;
        Impossible.moduleManager.onTick();
        for (Player player : mc.level.players()) {
            if (player == null || player.getHealth() > 0.0F)
                continue;
            EVENT_BUS.post(new DeathEvent(player));
        }
    }

    @Subscribe
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent event) {
        if (nullCheck()) return;
        if (event.getStage() == Stage.PRE) {
           // Impossible.rotationManager.updateRotations();
            Impossible.positionManager.updatePosition();
        }
        if (event.getStage() == Stage.POST) {
            Impossible.rotationManager.restoreRotations();
            Impossible.positionManager.restorePosition();
        }
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        Impossible.serverManager.onPacketReceived();
        if (event.getPacket() instanceof ClientboundSetTimePacket)
            Impossible.serverManager.update();
        if (event.getPacket() instanceof ClientboundCustomPayloadPacket(CustomPacketPayload payload)
                && payload instanceof BrandPayload(String brand)) {
            Impossible.serverManager.setServerBrand(brand);
        }
    }

    @Subscribe
    public void onWorldRender(Render3DEvent event) {
        Impossible.moduleManager.onRender3D(event);
    }

    @Subscribe
    public void onRenderGameOverlayEvent(Render2DEvent event) {
        Impossible.moduleManager.onRender2D(event);
    }

    @Subscribe
    public void onKeyInput(KeyInputEvent event) {
        Impossible.moduleManager.onKeyPressed(event.getKey());
    }

    @Subscribe
    public void onChatSent(ChatEvent event) {
        if (event.getMessage().startsWith(Command.getCommandPrefix())) {
            event.cancel();
            try {
                if (event.getMessage().length() > 1) {
                    Impossible.commandManager.executeCommand(event.getMessage().substring(Command.getCommandPrefix().length() - 1));
                } else {
                    Command.sendMessage("Please enter a command.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Command.sendMessage(ChatFormatting.RED + "An error occurred while running this command. Check the log!");
            }
        }
    }
}