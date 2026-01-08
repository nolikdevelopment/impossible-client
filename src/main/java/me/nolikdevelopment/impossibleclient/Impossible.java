package me.nolikdevelopment.impossibleclient;

import me.nolikdevelopment.impossibleclient.features.gui.HudEditorScreen;
import me.nolikdevelopment.impossibleclient.manager.*;
import me.nolikdevelopment.impossibleclient.util.TextUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Impossible implements ModInitializer, ClientModInitializer {
    public static final String VERSION = "v2 - 1.21.10 - 08/01/2026 19:11";
    public static final String CLIENT_NAME = "Impossible Client";
    public static final String NAME = CLIENT_NAME + " " + VERSION;
    public static boolean playSound;

    public static float TIMER = 1f;

    public static final Logger LOGGER = LogManager.getLogger("");
    public static ServerManager serverManager;
    public static ColorManager colorManager;
    public static RotateManager rotationManager;
    public static PositionManager positionManager;
    public static HoleManager holeManager;
    public static EventManager eventManager;
    public static SpeedManager speedManager;
    public static CommandManager commandManager;
    public static FriendManager friendManager;
    public static ModuleManager moduleManager;
    public static ConfigManager configManager;
    public static HudEditorScreen hudEditorScreen;


    @Override
    public void onInitialize() {
        eventManager = new EventManager();
        serverManager = new ServerManager();
        rotationManager = new RotateManager();
        positionManager = new PositionManager();
        friendManager = new FriendManager();
        colorManager = new ColorManager();
        commandManager = new CommandManager();
        moduleManager = new ModuleManager();
        speedManager = new SpeedManager();
        holeManager = new HoleManager();

        TextUtil.init();
    }

    @Override
    public void onInitializeClient() {
        LOGGER.info("Start Client");
        eventManager.init();
        moduleManager.init();
        rotationManager.init();
        hudEditorScreen = new HudEditorScreen();
        configManager = new ConfigManager();
        configManager.load();
        colorManager.init();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> configManager.save()));
    }
}