package net.prasyb.miraimcchat.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.prasyb.miraimcchat.service.ClientThreadService;

public class DisconnectCommand extends BaseCommand implements ICommand {
    public static final String name = "chatdisconnect";

    public DisconnectCommand() {
        super(name, true);
        this.setUsernameIndex(0);
    }

    @Override
    public String getUsage(ICommandSender commandSender) {
        return "/chatdisconnect";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        boolean isSuccess = ClientThreadService.stopWebSocketClient();
        if (isSuccess) {
            sender.sendMessage(new TextComponentString("已断开连接"));
        } else {
            sender.sendMessage(new TextComponentString("目前未连接"));
        }
    }
}
