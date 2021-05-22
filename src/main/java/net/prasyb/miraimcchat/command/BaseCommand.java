package net.prasyb.miraimcchat.command;

import net.minecraft.command.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseCommand implements ICommand {
    private String name;
    private boolean requiresOP;
    public int usernameIndex = -1;
    private final static int OP = 2;
    protected ArrayList<String> aliases;

    public BaseCommand(String name, boolean requiresOP) {
        this(name, requiresOP, null);
    }

    public BaseCommand(String name, boolean requiresOP, ArrayList<String> aliases) {
        this.name = name;
        this.requiresOP = requiresOP;
        this.aliases = (aliases == null) ? new ArrayList<>() : aliases;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/miraimcchat " + name;
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return !requiresOP || sender.canUseCommand(OP, name);
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (usernameIndex < 0) {
            return Collections.<String> emptyList();
        }
        return args.length == usernameIndex + 1 ? CommandBase.getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.<String> emptyList();
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return index == usernameIndex;
    }

    @Override
    public int compareTo(ICommand o) {
        return this.getName().compareTo(o.getName());
    }

    public void setUsernameIndex(int i) {
        usernameIndex = i;
    }
}
