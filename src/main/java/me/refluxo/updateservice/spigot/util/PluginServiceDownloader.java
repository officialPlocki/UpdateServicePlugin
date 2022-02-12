package me.refluxo.updateservice.spigot.util;

import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.SimplePluginManager;

import java.io.*;
import java.net.URL;
import java.util.UUID;

public class PluginServiceDownloader {

    public void loadPlugin(String key) {
        new Thread(() -> {
            File file = new File(".tmp/" + UUID.randomUUID() + ".jar");
            if(file.exists()) {
                file.delete();
            }
            BufferedInputStream inputStream = null;
            try {
                inputStream = new BufferedInputStream(new URL("http://addr:26/download?get=true&key=!!!ES_GIBT_KEINEN_KEY_DU_VOLLIDIOT!_!!!&resource="+key).openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while (true) {
                try {
                    assert inputStream != null;
                    if ((bytesRead = inputStream.read(buffer, 0, 1024)) == -1) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    assert outputStream != null;
                    outputStream.write(buffer, 0, bytesRead);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                assert outputStream != null;
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                new SimplePluginManager(Bukkit.getServer(), (SimpleCommandMap) Bukkit.getCommandMap()).loadPlugin(file);
            } catch (InvalidPluginException e) {
                e.printStackTrace();
            }
            Runtime.getRuntime().addShutdownHook(new Thread(file::delete));
        }).start();
    }

}
