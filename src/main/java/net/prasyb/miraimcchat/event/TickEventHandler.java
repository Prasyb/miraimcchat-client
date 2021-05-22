package net.prasyb.miraimcchat.event;

import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class TickEventHandler {
    private final Queue<String> toSendQueue;

    public Queue<String> getToSendQueue() {
        return toSendQueue;
    }

    public TickEventHandler() {
        toSendQueue = new LinkedList<>();
    }

    @SubscribeEvent
    public void onTickEvent(TickEvent.WorldTickEvent event) {
        String toSend = toSendQueue.poll();
        if (!event.world.isRemote && toSend != null) {
            TextComponentString textComponents = new TextComponentString(toSend);
            Objects.requireNonNull(event.world.getMinecraftServer()).sendMessage(textComponents);
            event.world.getMinecraftServer().getPlayerList().sendMessage(textComponents);
        }
    }
}
