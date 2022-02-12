package me.refluxo.updateservice.bungee;

import me.refluxo.updateservice.bungee.util.PluginManager;
import net.md_5.bungee.api.plugin.Plugin;

public class UpdateServicePluginBungee extends Plugin {

    @Override
    public void onEnable() {
        new PluginManager().loadPlugins();
    }

}
