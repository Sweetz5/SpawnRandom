package com.betalands.spawnrandom.config;

import org.bukkit.plugin.Plugin;
import org.bukkit.util.config.Configuration;

import java.io.File;
import java.io.IOException;

public class Config {

    private File file;
    private Configuration configuration;

    private int radius;

    public Config(Plugin plugin) {
        loadFile(plugin);
        loadProperties();
    }

    private void loadFile(Plugin plugin) {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        file = new File(plugin.getDataFolder(), "config.yml");
        boolean saveDefaults = false;

        if (!file.exists()) {
            try {
                file.createNewFile();
                saveDefaults = true;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        configuration = new Configuration(file);
        if (saveDefaults) {
            configuration.setProperty("radius", 1500);
            configuration.save();
        }
        configuration.load();
    }

    private void loadProperties() {
        radius = Integer.parseInt(configuration.getProperty("radius").toString());
    }

    public int getRadius() {
        return radius;
    }
}
