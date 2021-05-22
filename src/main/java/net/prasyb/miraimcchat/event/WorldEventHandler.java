package net.prasyb.miraimcchat.event;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.prasyb.miraimcchat.command.ConnectCommand;
import net.prasyb.miraimcchat.service.ClientThreadService;

public class WorldEventHandler {

    public static void onEntranceEvent(FMLServerStartingEvent event) {
        event.registerServerCommand(new ConnectCommand());
        ClientThreadService.runWebSocketClient();
    }

    public static void onExitEvent(FMLServerStoppingEvent event) {
        ClientThreadService.stopWebSocketClient();
    }
}
