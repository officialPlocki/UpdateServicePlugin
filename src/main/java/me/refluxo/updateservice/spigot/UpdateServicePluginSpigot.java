package me.refluxo.updateservice.spigot;

import me.refluxo.updateservice.spigot.util.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class UpdateServicePluginSpigot extends JavaPlugin {

    @Override
    public void onEnable() {
        new PluginManager().loadPlugins();
    }

}
