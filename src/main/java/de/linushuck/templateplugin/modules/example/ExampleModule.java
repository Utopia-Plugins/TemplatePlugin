package de.linushuck.templateplugin.modules.example;

import de.linushuck.helperlibary.modules.Module;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ExampleModule extends Module implements Listener
{
    public ExampleModule(JavaPlugin plugin)
    {
        super(plugin);
    }

    @Override
    public void load()
    {

    }

    @Override
    public void init()
    {
        Bukkit.getPluginManager().registerEvents(this, getPlugin());
    }

    @Override
    public void unload()
    {
        HandlerList.unregisterAll(this);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        event.getPlayer().sendMessage("You broke a block!");
    }
}
