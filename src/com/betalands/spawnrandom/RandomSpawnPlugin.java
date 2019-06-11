package com.betalands.spawnrandom;

import com.betalands.spawnrandom.config.Config;
import com.betalands.spawnrandom.listener.SpawnListeners;
import com.betalands.spawnrandom.model.LocationManager;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomSpawnPlugin extends JavaPlugin {

    private Config config;
    private LocationManager locationManager;

    private SpawnListeners spawnListeners;

    @Override
    public void onEnable() {
        config = new Config(this);
        locationManager = new LocationManager(this);

        spawnListeners = new SpawnListeners(this);

        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvent(Event.Type.PLAYER_JOIN, spawnListeners, Event.Priority.Normal, this); // Forgot how archaic this old event system was lmao
        pluginManager.registerEvent(Event.Type.PLAYER_RESPAWN, spawnListeners, Event.Priority.Normal, this);

    }

    @Override
    public void onDisable() {

    }

    public Config getConfig() {
        return config;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }
}
