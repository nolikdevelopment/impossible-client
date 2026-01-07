package me.nolikdevelopment.impossibleclient.manager;

import me.nolikdevelopment.impossibleclient.features.Feature;
import me.nolikdevelopment.impossibleclient.util.MathUtil;
import me.nolikdevelopment.impossibleclient.util.traits.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class RotateManager extends Feature {
    // TODO: переписать
    private float yaw, pitch;
    private boolean rotating;
    private long lastRotationTime;

    public void init() {
        Util.EVENT_BUS.register(this);
    }

    public void rotate(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.lastRotationTime = System.currentTimeMillis();
        this.rotating = true;

        sendRotationPacket(yaw, pitch);
    }

    public void rotateToVec3d(Vec3 vec) {
        float[] angles = MathUtil.calcAngle(mc.player.getEyePosition(), vec);
        rotate(angles[0], angles[1]);
    }

    public void rotateToEntity(Entity entity) {
        rotateToVec3d(entity.getEyePosition());
    }

    public void rotateToBlockPos(BlockPos pos) {
        rotateToVec3d(new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));
    }

    private void sendRotationPacket(float yaw, float pitch) {
        if (mc.player != null || mc.getConnection() != null) {
            mc.getConnection().send(new ServerboundMovePlayerPacket.Rot(yaw, pitch, mc.player.onGround(), mc.player.horizontalCollision));
            restoreRotations();
        }
    }

    public void restoreRotations() {
        this.rotating = false;
    }

    public boolean isRotating() {
        return rotating && (System.currentTimeMillis() - lastRotationTime < 1000);
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }
}