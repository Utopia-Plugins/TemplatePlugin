package de.linushuck.templateplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class CommandTabHandler implements TabCompleter, CommandExecutor
{
    private final boolean console;
    private final String permission;


    public CommandTabHandler(boolean console, String permission)
    {
        this.console = console;
        this.permission = permission;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if(! console && ! (commandSender instanceof Player))
        {
            commandSender.sendMessage("You must be a player to execute this command!");
            return true;
        }
        else if(permission != null && ! commandSender.hasPermission(permission))
        {
            commandSender.sendMessage("You don't have the permission to execute this command!");
            return true;
        }
        return onCommand(commandSender, strings);
    }

    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings)
    {
        return onTab(commandSender, strings);
    }

    public abstract boolean onCommand(CommandSender commandSender, String[] arguments);

    public abstract List<String> onTab(CommandSender commandSender, String[] arguments);
}
