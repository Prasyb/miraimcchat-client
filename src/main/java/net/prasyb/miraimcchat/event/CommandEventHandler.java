package net.prasyb.miraimcchat.event;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.prasyb.miraimcchat.command.ConnectCommand;
import net.prasyb.miraimcchat.command.DisconnectCommand;

@Mod.EventBusSubscriber
public class CommandEventHandler {
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
        dispatcher.register(
                Commands.literal("mcchat")
                        .then(ConnectCommand.register())
                        .then(DisconnectCommand.register())
        );
    }
}
