package net.prasyb.miraimcchat.event;


import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.prasyb.miraimcchat.service.MessageService;


public class ChatEventHandler {
    @SubscribeEvent
    public void onChatEvent(ServerChatEvent event) {
        MessageService.sendMessage(event);
    }
}
