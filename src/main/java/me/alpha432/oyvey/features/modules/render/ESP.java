package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.event.impl.Render3DEvent;
import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.Feature;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.render.RenderUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.AABB;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
 // TODO : Пустой рендер если ты подобрал предмет
public class ESP extends Module {
    List<Entity> pizdec = new ArrayList<>();

    public ESP() {
        super("ESP", "", Category.RENDER);
    }

    @Subscribe
    public void onTick() {
        for (Entity entity : mc.level.entitiesForRendering()) {
            if (entity == mc.player) continue;
            if (!isEntity(entity)) continue;
            pizdec.add(entity);
        }
    }
    @Subscribe public void onRender3D(Render3DEvent event) {
       if (Feature.nullCheck()) return;
       if (pizdec.isEmpty()) return;
       for (Entity entity : pizdec) {
           AABB box = entity.getBoundingBox();
           double x = box.minX;
           double y = box.minY;
           double z = box.minZ;
           double x1 = box.maxX;
           double y1 = box.maxY;
           double z1 = box.maxZ;
           AABB renderBox = new AABB(x, y, z, x1, y1, z1);
           RenderUtil.drawBox(event.getMatrix(), renderBox, Color.WHITE, 1);
       }
    }
    private boolean isEntity(Entity entity) {
        if (entity.getType() == EntityType.ITEM) return true;
        return false;
    }
}
