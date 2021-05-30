package net.prasyb.miraimcchat.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.prasyb.miraimcchat.ModConfig;
import net.prasyb.miraimcchat.service.ClientThreadService;

public class StatusCommand {
    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("status").executes(StatusCommand::execute);
    }
    public static int execute(CommandContext<CommandSource> context) throws CommandException {
        boolean clientEnabled = ModConfig.IS_ENABLED.get();
        boolean receiveEnabled = ModConfig.RECEIVE_ENABLED.get();
        boolean sendEnabled = ModConfig.SEND_ENABLED.get();
        boolean connected = ClientThreadService.client != null;
        String host = ModConfig.HOST.get();
        int port = ModConfig.PORT.get();
        String key = ModConfig.KEY.get();
        String toSend = "目标服务器:" + host + ":" + port + "\n"
                + "key:" + key + "\n"
                + "全局服务开启:" + clientEnabled + "\n"
                + "接收消息开启:" + receiveEnabled + "\n"
                + "发送消息开启:" + sendEnabled + "\n"
                + "连接状态:" + connected;
        context.getSource().sendFeedback(new StringTextComponent(toSend), true);
        return 0;
    }
}
