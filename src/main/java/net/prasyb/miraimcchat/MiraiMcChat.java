package net.prasyb.miraimcchat;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.Mod;
import net.prasyb.miraimcchat.config.WebSocketClientConfig;
import net.prasyb.miraimcchat.event.ChatEventHandler;
import net.prasyb.miraimcchat.event.TickEventHandler;
import net.prasyb.miraimcchat.event.WorldEventHandler;
import net.prasyb.miraimcchat.network.WebSocketClient;
import net.prasyb.miraimcchat.registry.ConfigRegistry;
import org.apache.logging.log4j.Logger;


@Mod(
        modid = MiraiMcChat.MOD_ID,
        name = MiraiMcChat.MOD_NAME,
        version = MiraiMcChat.VERSION,
        acceptedMinecraftVersions = "[1.12,1.13)"
)
public class MiraiMcChat {

    public static final String MOD_ID = "miraimcchatclient";
    public static final String MOD_NAME = "MiraiMcChat-client";
    public static final String VERSION = "0.0.1";

    private static Logger logger;
    private WebSocketClient clientThread;
    private WebSocketClientConfig clientConfig;
    private TickEventHandler tickEventHandler;

    @Mod.Instance(MOD_ID)
    public static MiraiMcChat INSTANCE;

    public TickEventHandler getTickEventHandler() { return tickEventHandler; }

    public Logger getLogger() {
        return logger;
    }

    public WebSocketClient getClientThread() {
        return clientThread;
    }

    public WebSocketClientConfig getClientConfig() {
        return clientConfig;
    }

    public void setClientThread(WebSocketClient clientThread) {
        this.clientThread = clientThread;
    }


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        tickEventHandler = new TickEventHandler();
        ConfigRegistry.init(new Configuration(event.getSuggestedConfigurationFile()));
        clientConfig = new WebSocketClientConfig();
        ConfigRegistry.register(clientConfig);
        ConfigRegistry.syncAllConfig();
        MinecraftForge.EVENT_BUS.register(new ChatEventHandler());
        MinecraftForge.EVENT_BUS.register(tickEventHandler);
    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }


    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Mod.EventHandler
    public void onEntranceEvent(FMLServerStartingEvent event) {
        WorldEventHandler.onEntranceEvent(event);
    }

    @Mod.EventHandler
    public void onExitEvent(FMLServerStoppingEvent event) {
        WorldEventHandler.onExitEvent(event);
    }
}
