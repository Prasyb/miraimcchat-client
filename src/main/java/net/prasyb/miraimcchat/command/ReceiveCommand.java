package net.prasyb.miraimcchat.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.prasyb.miraimcchat.ModConfig;

public class ReceiveCommand {
    public static ArgumentBuilder<CommandSource, ?> register() {
    return Commands.literal("receive")
            .then(Commands.argument("enabled", BoolArgumentType.bool())
            .executes(ReceiveCommand::execute));
    }
    public static int execute(CommandContext<CommandSource> context) throws CommandException {
        boolean isEnabled = context.getArgument("enabled", Boolean.class);
        ModConfig.RECEIVE_ENABLED.set(isEnabled);
        context.getSource().sendFeedback(
                new StringTextComponent("接收消息开关已被设置为 " + isEnabled), true);
        return 0;
    }
}
