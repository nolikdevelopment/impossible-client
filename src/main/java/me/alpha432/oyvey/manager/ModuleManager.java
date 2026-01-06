package me.alpha432.oyvey.manager;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.alpha432.oyvey.event.impl.Render2DEvent;
import me.alpha432.oyvey.event.impl.Render3DEvent;
import me.alpha432.oyvey.features.Feature;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.modules.client.ClickGui;
import me.alpha432.oyvey.features.modules.client.HudEditor;
import me.alpha432.oyvey.features.modules.client.Notifications;
import me.alpha432.oyvey.features.modules.combat.*;
import me.alpha432.oyvey.features.modules.hud.Coordinates;
import me.alpha432.oyvey.features.modules.hud.Watermark;
import me.alpha432.oyvey.features.modules.misc.*;
import me.alpha432.oyvey.features.modules.movement.*;
import me.alpha432.oyvey.features.modules.player.*;
import me.alpha432.oyvey.features.modules.render.*;
import me.alpha432.oyvey.util.traits.Jsonable;
import me.alpha432.oyvey.util.traits.Util;

import java.util.*;
import java.util.stream.Stream;

public class ModuleManager implements Jsonable, Util {
    private final Map<Class<? extends Module>, Module> fastRegistry = new HashMap<>();
    private final List<Module> modules = new ArrayList<>();

    public void init() {
        register(new Watermark());
        register(new Coordinates());
        register(new HudEditor());
        register(new ClickGui());
        register(new Notifications());
        register(new Criticals());
        register(new MCF());
        register(new Step());
        register(new FastFall());
        register(new FastPlace());
        register(new Velocity());
        register(new BlockHighlight());
        register(new FullBright());
        register(new XCarry());
        register(new NoFall());
        register(new BoatFly());
        register(new NoRender());
        register(new Sprint());
        register(new FOV());
        register(new HighJump());
        register(new NoJumpDelay());
        register(new NoSlow());
        register(new LevitationControl());
        register(new CoordinateLogger());
        register(new Flight());
        register(new AutoRespawn());
        register(new AutoWalk());
        register(new Reach());
        register(new Scaffold());
        register(new AntiLiquid());
        register(new AutoFarm());
        register(new AntiSpam());
        register(new AntiHunger());
        register(new AutoNameTag());
        register(new AutoFish());
        register(new ESP());
        register(new NoBob());
        register(new ElytraFly());
        register(new MultiTask());
        register(new AutoMiner());
        register(new Aura());
        register(new Announcer());
        register(new OffHand());
        register(new HitSound());
        register(new AutoPearl());
        register(new Spammer());
        register(new AutoSign());
        register(new TestModule());
    }

    public void register(Module module) {
        getModules().add(module);
        fastRegistry.put(module.getClass(), module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public Stream<Module> stream() {
        return getModules().stream();
    }

    public <T extends Module> T getModuleByClass(Class<T> clazz) {
        return (T) fastRegistry.get(clazz);
    }

    public Module getModuleByName(String name) {
        return stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Module getModuleByDisplayName(String display) {
        return stream().filter(m -> m.getDisplayName().equalsIgnoreCase(display)).findFirst().orElse(null);
    }

    public List<Module> getModulesByCategory(Module.Category category) {
        return stream().filter(m -> m.getCategory() == category).toList();
    }

    public List<Module.Category> getCategories() {
        return Arrays.asList(Module.Category.values());
    }

    public void onLoad() {
        getModules().forEach(Module::onLoad);
    }

    public void onTick() {
        stream().filter(Feature::isEnabled).forEach(Module::onTick);
    }

    public void onRender2D(Render2DEvent event) {
        stream().filter(Feature::isEnabled).forEach(module -> module.onRender2D(event));
    }

    public void onRender3D(Render3DEvent event) {
        stream().filter(Feature::isEnabled).forEach(module -> module.onRender3D(event));
    }

    public void onUnload() {
        getModules().forEach(EVENT_BUS::unregister);
        getModules().forEach(Module::onUnload);
    }

    public void onKeyPressed(int key) {
        if (key <= 0 || mc.screen != null) return;
        stream().filter(module -> module.getBind().getKey() == key).forEach(Module::toggle);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        for (Module module : getModules()) {
            object.add(module.getName(), module.toJson());
        }
        return object;
    }

    @Override
    public void fromJson(JsonElement element) {
        for (Module module : getModules()) {
            module.fromJson(element.getAsJsonObject().get(module.getName()));
        }
    }

    @Override
    public String getFileName() {
        return "modules.json";
    }
}
