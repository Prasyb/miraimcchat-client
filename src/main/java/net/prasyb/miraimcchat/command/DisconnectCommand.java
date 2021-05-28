package net.prasyb.miraimcchat.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.prasyb.miraimcchat.service.ClientThreadService;

public class DisconnectCommand  {
    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("disconnect")
                .then(Commands.argument("arguments", StringArgumentType.greedyString())
                        .executes(ConnectCommand::execute));
    }
    public static int execute(CommandContext<CommandSource> context) throws CommandException {
        boolean isSuccess = ClientThreadService.stopWebSocketClient();
        if (isSuccess) {
            context.getSource().sendFeedback(new StringTextComponent("已断开连接"), true);
        } else {
            context.getSource().sendFeedback(new StringTextComponent("目前未连接"), true);
        }
        return 0;
    }
}
