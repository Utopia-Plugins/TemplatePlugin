package de.linushuck.templateplugin;

import de.linushuck.helperlibary.modules.ModuleHandler;
import de.linushuck.helperlibary.modules.language.LanguageModule;
import de.linushuck.helperlibary.utils.Logger;
import de.linushuck.helperlibary.utils.PluginUtils;
import de.linushuck.helperlibary.utils.ResourceUtils;
import de.linushuck.templateplugin.commands.CommandRouter;
import de.linushuck.templateplugin.commands.ModuleCommands;
import de.linushuck.templateplugin.modules.example.ExampleModule;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class TemplatePlugin extends JavaPlugin
{
    @Getter
    private static TemplatePlugin instance;

    @Getter
    ModuleHandler moduleHandler;

    @Override
    public void onEnable()
    {
        instance = this;
        ResourceUtils.saveResource(this,"plugin.yml", true);

        moduleHandler = new ModuleHandler();
        moduleHandler.addModule("ExampleModule", new ExampleModule(this));
        moduleHandler.addModule("LanguageModule", new LanguageModule(this));

        moduleHandler.loadModules();

        activateCommands();

        Logger.info(this,"Template", "<green>" + PluginUtils.compiledAgoMessage(this) + "</green>");
    }

    @Override
    public void onDisable()
    {
        moduleHandler.unloadModules();
        instance = null;
    }


    private void activateCommands()
    {
        CommandRouter skyblockhelper = new CommandRouter("templateplugin");
        skyblockhelper.addSubCommand("module", new ModuleCommands());
    }
}
