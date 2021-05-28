package net.prasyb.miraimcchat.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.prasyb.miraimcchat.ModConfig;
import net.prasyb.miraimcchat.service.ClientThreadService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectCommand {
    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("connect")
                .then(Commands.argument("arguments", StringArgumentType.greedyString())
                        .executes(ConnectCommand::execute));
    }
    public static int execute(CommandContext<CommandSource> context) throws CommandException {
        String[] args = context.getInput().split("\\s+");
        switch(args.length) {
            default: {
                context.getSource().sendFeedback(new StringTextComponent("参数不合法"), true);
                return 0;
            }
            case 4: {
                Pattern pattern = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+)");
                Matcher matcher = pattern.matcher(args[2]);
                if (matcher.find()) {
                    ModConfig.HOST.set(matcher.group(1));
                    ModConfig.PORT.set(Integer.parseInt(matcher.group(2)));
                    ModConfig.KEY.set(args[3]);
                    context.getSource().sendFeedback(new StringTextComponent("已保存，正在尝试重新建立连接"), true);
                    ClientThreadService.runWebSocketClient();
                } else {
                    context.getSource().sendFeedback(new StringTextComponent("格式错误"), true);
                }
                return 0;
            }
            case 3: {
                Pattern pattern = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+)");
                Matcher matcher = pattern.matcher(args[2]);
                if(matcher.find()) {
                    ModConfig.HOST.set(matcher.group(1));
                    ModConfig.PORT.set(Integer.parseInt(matcher.group(2)));
                    context.getSource().sendFeedback(new StringTextComponent("已保存，正在尝试重新建立连接"), true);
                    ClientThreadService.runWebSocketClient();
                } else {
                    context.getSource().sendFeedback(new StringTextComponent("格式错误"), true);
                }
            }
            case 2: {
                context.getSource().sendFeedback(new StringTextComponent("尝试建立连接"), true);
                ClientThreadService.runWebSocketClient();
            }
        }
        return 0;
    }
}
