package me.nolikdevelopment.impossibleclient.features.modules.movement;

import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

public class AntiVoid extends Module {
    public AntiVoid() {
        super("AntiVoid", "", Category.MOVEMENT);
    }
    @Subscribe public void onTick() {
        if (isFall()) {
            mc.player.setDeltaMovement(mc.player.getDeltaMovement().x, 3, mc.player.getDeltaMovement().z);
        }
    }
    private static boolean isFall() {
        for (int i = (int) mc.player.getY(); i >=-64 ; i--) {
            if (!mc.level.isEmptyBlock(BlockPos.containing(mc.player.getX(), i, mc.player.getZ()))) {
                return false;
            }
        }
        return mc.player.fallDistance > 0;
    }
}
