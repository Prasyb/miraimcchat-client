package net.prasyb.miraimcchat.event;


import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.prasyb.miraimcchat.service.ClientThreadService;

@Mod.EventBusSubscriber
public class WorldEventHandler {

    @SubscribeEvent
    public static void onEntranceEvent(FMLServerStartingEvent event) {
        ClientThreadService.runWebSocketClient();
    }

    @SubscribeEvent
    public static void onExitEvent(FMLServerStoppingEvent event) {
        ClientThreadService.stopWebSocketClient();
    }
}
