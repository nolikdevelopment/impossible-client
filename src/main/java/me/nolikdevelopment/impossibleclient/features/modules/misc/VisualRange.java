package me.nolikdevelopment.impossibleclient.features.modules.misc;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.player.ChatUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.awt.*;
import java.util.ArrayList;

public class VisualRange extends Module {
    public ArrayList<Player> players = new ArrayList<>();
    public VisualRange() {
        super("VisualRange", "", Category.MISC);
    }
    @Subscribe public void onTick() {
        for (Entity entity : mc.level.players()) {
            if (entity instanceof Player player) {
                if (entity == mc.player) continue;
                if (!players.contains(player)) {
                    players.add(player);
                    ChatUtil.sendMessage(Component.literal("Игрок " + player.getName().getString() + " попал в вашу зону видимости"));
                    mc.player.playSound(SoundEvents.ALLAY_HURT, 1, 1);
                }
            }
        }
    }
    @Subscribe public void onDisable() {
        players.clear();
    }
    @Subscribe public void onEnable() {
        players.clear();
    }
}
