package de.linushuck.templateplugin.commands;

import de.linushuck.templateplugin.TemplatePlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ModuleCommands extends CommandTabHandler
{
    public ModuleCommands()
    {
        super(false, "template.modules");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, String[] arguments)
    {
        Player player = (Player) commandSender;
        if(arguments.length != 3)
        {
            player.sendMessage("§cInvalid arguments!");
            return false;
        }
        String module = arguments[2];
        switch(arguments[1])
        {
            case "load" ->
            {
                player.sendMessage("§cLoading module...");
                if(TemplatePlugin.getInstance().getModuleHandler().loadModule(module))
                {
                    player.sendMessage("§aLoaded module!");
                }
                else
                {
                    player.sendMessage("§cModule was already loaded!");
                }
            }
            case "unload" ->
            {
                player.sendMessage("§cUnloading module...");
                if(TemplatePlugin.getInstance().getModuleHandler().unloadModule(module))
                {
                    player.sendMessage("§aUnloaded module!");
                }
                else
                {
                    player.sendMessage("§cModule was not loaded!");
                }
            }
            case "reload" ->
            {
                player.sendMessage("§cReloading module...");
                if(TemplatePlugin.getInstance().getModuleHandler().unloadModule(module))
                {
                    if(TemplatePlugin.getInstance().getModuleHandler().loadModule(module))
                    {
                        player.sendMessage("§aReloaded module!");
                    }
                    else
                    {
                        player.sendMessage("§cModule was already loaded!");
                    }
                }
                else
                {
                    player.sendMessage("§cModule was not loaded! Loading...");
                    if(TemplatePlugin.getInstance().getModuleHandler().loadModule(module))
                    {
                        player.sendMessage("§aReloaded module!");
                    }
                    else
                    {
                        player.sendMessage("§cModule was already loaded!");
                    }
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTab(CommandSender commandSender, String[] arguments)
    {
        switch(arguments.length)
        {
            case 2 ->
            {
                return List.of("load", "unload", "reload");
            }
            case 3 ->
            {
                return TemplatePlugin.getInstance().getModuleHandler().getNames();
            }
        }
        return List.of();
    }
}
