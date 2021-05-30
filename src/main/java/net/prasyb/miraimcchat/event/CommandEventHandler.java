package net.prasyb.miraimcchat.event;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.prasyb.miraimcchat.command.*;

@Mod.EventBusSubscriber
public class CommandEventHandler {
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
        dispatcher.register(
                Commands.literal("mcchat")
                        .requires(commandSource -> commandSource.hasPermissionLevel(2))
                        .then(ConnectCommand.register())
                        .then(DisconnectCommand.register())
                        .then(ReceiveCommand.register())
                        .then(SendCommand.register())
                        .then(StatusCommand.register())
        );
    }
}
