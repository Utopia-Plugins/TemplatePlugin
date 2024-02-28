package de.linushuck.templateplugin.commands;

import de.linushuck.templateplugin.TemplatePlugin;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;

public class CommandRouter extends CommandTabHandler
{
    private final HashMap<String, CommandTabHandler> commands = new HashMap<>();

    public CommandRouter(String command)
    {
        super(true, null);
        TemplatePlugin.getInstance().getCommand(command).setExecutor(this);
        TemplatePlugin.getInstance().getCommand(command).setTabCompleter(this);
    }

    public void addSubCommand(String command, CommandTabHandler handler)
    {
        commands.put(command, handler);
    }


    @Override
    public boolean onCommand(CommandSender commandSender, String[] arguments)
    {
        if(arguments.length == 0)
        {
            return false;
        }
        else if(commands.containsKey(arguments[0]))
        {
            return commands.get(arguments[0]).onCommand(commandSender, arguments);
        }
        else
        {
            return false;
        }
    }

    @Override
    public List<String> onTab(CommandSender commandSender, String[] arguments)
    {
        if(arguments.length == 1)
        {
            return commands.keySet().stream().toList();
        }
        else if(commands.containsKey(arguments[0]))
        {
            return commands.get(arguments[0]).onTab(commandSender, arguments);
        }
        else
        {
            return List.of();
        }
    }
}
