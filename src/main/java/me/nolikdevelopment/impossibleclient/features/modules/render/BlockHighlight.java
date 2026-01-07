package me.nolikdevelopment.impossibleclient.features.modules.render;

import me.nolikdevelopment.impossibleclient.event.impl.Render3DEvent;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.features.settings.Setting;
import me.nolikdevelopment.impossibleclient.util.render.RenderUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.awt.*;

public class BlockHighlight extends Module {
    public Setting<Color> color = color("Color", 255, 0, 0, 255);
    public Setting<Float> lineWidth = num("LineWidth", 1.0f, 0.1f, 5.0f);

    public BlockHighlight() {
        super("BlockHighlight", "Draws box at the block that you are looking at", Category.RENDER);
    }

    @Subscribe
    public void onRender3D(Render3DEvent event) {
        if (mc.hitResult instanceof BlockHitResult result) {
            VoxelShape shape = mc.level.getBlockState(result.getBlockPos()).getShape(mc.level, result.getBlockPos());
            if (shape.isEmpty()) return;
            AABB box = shape.bounds();
            box = box.move(result.getBlockPos());
            RenderUtil.drawBox(event.getMatrix(), box, color.getValue(), lineWidth.getValue());
        }
    }
}
