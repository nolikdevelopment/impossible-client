package me.nolikdevelopment.impossibleclient.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.Feature;
import me.nolikdevelopment.impossibleclient.features.settings.Bind;
import me.nolikdevelopment.impossibleclient.features.settings.EnumConverter;
import me.nolikdevelopment.impossibleclient.features.settings.Setting;
import me.nolikdevelopment.impossibleclient.util.traits.Jsonable;
import net.fabricmc.loader.api.FabricLoader;
import org.joml.Vector2f;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ConfigManager {
    private static final Path IMPOSSIBLE_PATH = FabricLoader.getInstance().getGameDir().resolve("impossible");
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    private final List<Jsonable> jsonables = List.of(Impossible.friendManager, Impossible.moduleManager, Impossible.commandManager);

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void setValueFromJson(Feature feature, Setting setting, JsonElement element) {
        if (element == null || element.isJsonNull()) return;
        String str;
        switch (setting.getType()) {
            case "Boolean" -> {
                setting.setValue(element.getAsBoolean());
            }
            case "Double" -> {
                setting.setValue(element.getAsDouble());
            }
            case "Float" -> {
                setting.setValue(element.getAsFloat());
            }
            case "Integer" -> {
                setting.setValue(element.getAsInt());
            }
            case "String" -> {
                str = element.getAsString();
                setting.setValue(str.replace("_", " "));
            }
            case "Bind" -> {
                setting.setValue(new Bind(element.getAsInt()));
            }
            case "Color" -> {
                try {
                    String colorStr = element.getAsString();
                    String[] parts = colorStr.split(",");
                    if (parts.length == 4) {
                        int r = Integer.parseInt(parts[0]);
                        int g = Integer.parseInt(parts[1]);
                        int b = Integer.parseInt(parts[2]);
                        int a = Integer.parseInt(parts[3]);
                        setting.setValue(new Color(r, g, b, a));
                    }
                } catch (Exception exception) {
                    Impossible.LOGGER.error("Error parsing color for: {} : {}", feature.getName(), setting.getName());
                }
            }
            case "Pos" -> {
                try {
                    String posStr = element.getAsString();
                    String[] parts = posStr.split(",");
                    if (parts.length == 2) {
                        float x = Float.parseFloat(parts[0]);
                        float y = Float.parseFloat(parts[1]);
                        setting.setValue(new Vector2f(x, y));
                    }
                } catch (Exception exception) {
                    Impossible.LOGGER.error("Error parsing position for: {} : {}", feature.getName(), setting.getName());
                }
            }
            case "Enum" -> {
                try {
                    EnumConverter converter = new EnumConverter((Class<? extends Enum<?>>) setting.getValue().getClass());
                    Enum value = converter.doBackward(element);
                    setting.setValue(value);
                } catch (Exception exception) {
                }
            }
            default -> {
                Impossible.LOGGER.error("Unknown Setting type for: {} : {}", feature.getName(), setting.getName());
            }
        }
    }

    public void load() {
        if (!IMPOSSIBLE_PATH.toFile().exists()) IMPOSSIBLE_PATH.toFile().mkdirs();
        for (Jsonable jsonable : jsonables) {
            try {
                String read = Files.readString(IMPOSSIBLE_PATH.resolve(jsonable.getFileName()));
                jsonable.fromJson(JsonParser.parseString(read));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public void save() {
        if (!IMPOSSIBLE_PATH.toFile().exists()) IMPOSSIBLE_PATH.toFile().mkdirs();
        for (Jsonable jsonable : jsonables) {
            try {
                JsonElement json = jsonable.toJson();
                Files.writeString(IMPOSSIBLE_PATH.resolve(jsonable.getFileName()), gson.toJson(json));
            } catch (Throwable e) {
            }
        }
    }
}
