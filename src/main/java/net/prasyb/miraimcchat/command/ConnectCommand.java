package net.prasyb.miraimcchat.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.prasyb.miraimcchat.MiraiMcChat;
import net.prasyb.miraimcchat.registry.ConfigRegistry;
import net.prasyb.miraimcchat.service.ClientThreadService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectCommand extends BaseCommand implements ICommand {
    public static final String name = "chatconnect";

    public ConnectCommand() {
        super(name, true);
        this.setUsernameIndex(0);
    }

    @Override
    public String getUsage(ICommandSender commandSender) {
        return "/chatconnect" + " [ip:port]" + " [key]";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        switch(args.length) {
            default: {
                sender.sendMessage(new TextComponentString("请输入合法参数"));
                return;
            }
            case 2: {
                Pattern pattern = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+)");
                Matcher matcher = pattern.matcher(args[0]);
                if (matcher.find()) {
                    ConfigRegistry.getConfig().get("connection", "host", "127.0.0.1")
                            .set(matcher.group(1));
                    ConfigRegistry.getConfig().get("connection", "port", 0)
                            .set(Integer.parseInt(matcher.group(2)));
                    ConfigRegistry.getConfig().get("connection", "key", "")
                            .set(args[1]);
                    ConfigRegistry.getConfig().save();
                    MiraiMcChat.INSTANCE.getClientConfig().syncConfig(ConfigRegistry.getConfig());
                    sender.sendMessage(new TextComponentString("已保存，正在尝试重新建立连接"));
                    ClientThreadService.runWebSocketClient();
                } else {
                    sender.sendMessage(new TextComponentString("格式错误"));
                }
                return;
            }
            case 1: {
                Pattern pattern = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+)");
                Matcher matcher = pattern.matcher(args[0]);
                if(matcher.find()) {
                    ConfigRegistry.getConfig().get("connection", "host", "127.0.0.1")
                            .set(matcher.group(1));
                    ConfigRegistry.getConfig().get("connection", "port", 0)
                            .set(Integer.parseInt(matcher.group(2)));
                    ConfigRegistry.getConfig().save();
                    MiraiMcChat.INSTANCE.getClientConfig().syncConfig(ConfigRegistry.getConfig());
                    sender.sendMessage(new TextComponentString("已保存，正在尝试重新建立连接"));
                    ClientThreadService.runWebSocketClient();
                }
            }
            case 0: {
                sender.sendMessage(new TextComponentString("尝试建立连接"));
                ClientThreadService.runWebSocketClient();
            }
        }
    }
}
