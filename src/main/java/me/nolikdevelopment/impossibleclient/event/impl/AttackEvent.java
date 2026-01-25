package me.nolikdevelopment.impossibleclient.event.impl;

import me.nolikdevelopment.impossibleclient.event.Event;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class AttackEvent extends Event {
    private Entity entity;
    private Player player;
    public AttackEvent(Player player, Entity entity) {
        this.entity = entity;
        this.player = player;
    }

    public Entity getEntity() {
        return entity;
    }

    public Player getPlayer() {
        return player;
    }
}
