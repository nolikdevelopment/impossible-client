package me.alpha432.oyvey.event.impl;

import me.alpha432.oyvey.event.Event;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class HitEvent extends Event {
    private Entity entity;
    private Player player;
    public HitEvent(Entity entity, Player player) {
        this.entity = entity;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Entity getEntity() {
        return entity;
    }
}
