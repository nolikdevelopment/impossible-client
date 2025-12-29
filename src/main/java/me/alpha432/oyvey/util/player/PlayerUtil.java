package me.alpha432.oyvey.util.player;

import me.alpha432.oyvey.util.traits.Util;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class PlayerUtil implements Util {
    public static boolean isDead() {
        if (mc.player.isDeadOrDying()) return true;
        return false;
    }
    public static boolean isUsingItem() {
        if (mc.player.isUsingItem()) return true;
        return false;
    }
    public static boolean isSneaking() {
        if (mc.player.isMovingSlowly()) return true;
        return false;
    }
    public static boolean isSprinting() {
       if (mc.player.isSprinting()) return true;
       return false;
    }
    public static boolean isChatOpen() {
        if (mc.screen instanceof ChatScreen) return true;
        return false;
    }
    public static boolean isInventoryOpen() {
        if (mc.screen instanceof InventoryScreen) return true;
        return false;

    }
    public static boolean isInLiquid() {
        if (mc.player.isInLiquid()) return true;
        return false;
    }
    public static float getYaw() {
        return mc.player.getYRot();
    }
    public static float getPitch() {
        return mc.player.getXRot();
    }
    public static boolean isTargetEntity() {
        if (mc.hitResult instanceof EntityHitResult) return true;
        return false;
    }
    public static boolean isTargetBlock() {
        if (mc.hitResult instanceof BlockHitResult) return true;
        return false;
    }
    public static boolean isInvisible() {
        if (mc.player.isInvisible()) return true;
        return false;
    }
    public static boolean isGliding() {
        if (mc.player.isFallFlying()) return true;
        return false;
    }
    public static boolean isMoving() {
        if (mc.player.getSpeed() > 0) return true;
        return false;
    }
    public static boolean setSprinting() {
        mc.player.setSprinting(true);
        return false;
    }
}
