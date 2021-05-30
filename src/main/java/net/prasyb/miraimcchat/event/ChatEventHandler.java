package net.prasyb.miraimcchat.event;


import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.prasyb.miraimcchat.ModConfig;
import net.prasyb.miraimcchat.service.MessageService;

@Mod.EventBusSubscriber
public class ChatEventHandler {
    @SubscribeEvent
    public static void onChatEvent(ServerChatEvent event) {
        if (ModConfig.SEND_ENABLED.get()) {
            MessageService.sendMessage(event);
        }
    }
}
