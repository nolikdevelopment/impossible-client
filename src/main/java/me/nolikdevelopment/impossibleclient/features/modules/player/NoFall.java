package me.nolikdevelopment.impossibleclient.features.modules.player;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;

public class NoFall extends Module {
    public NoFall() {
        super("NoFall", "Removes fall damage", Category.PLAYER);
    }

    @Override
    public void onTick() {
        if (!mc.player.onGround() && Impossible.positionManager.getFallDistance() > 3) {
            boolean bl = mc.player.horizontalCollision;
            ServerboundMovePlayerPacket.PosRot pakcet = new ServerboundMovePlayerPacket.PosRot(mc.player.getX(), mc.player.getY() + 0.000000001, mc.player.getZ(),
                    mc.player.getYRot(), mc.player.getXRot(), false, bl);
            mc.player.connection.send(pakcet);
        }
    }
}
