package me.refluxo.updateservice.bungee.util;

import me.refluxo.updateservice.common.files.FileBuilder;
import me.refluxo.updateservice.common.files.YamlConfiguration;

import java.io.IOException;
import java.util.ArrayList;

public class PluginManager {

    private final YamlConfiguration yml;

    public PluginManager() {
        FileBuilder fb = new FileBuilder("config/resources.yml");
        yml = fb.getYaml();
        if(!fb.getFile().exists()) {
            try {
                fb.getFile().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            yml.set("downloads", new ArrayList<String>());
            fb.save();
        }
    }

    public void loadPlugins() {
        for(String resource : yml.getStringList("downloads")) {
            new PluginServiceDownloader().loadPlugin(resource);
        }
    }

}
