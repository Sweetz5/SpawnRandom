package com.betalands.spawnrandom.listener;

import com.betalands.spawnrandom.RandomSpawnPlugin;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.io.File;

public class SpawnListeners extends PlayerListener {

    private RandomSpawnPlugin plugin;

    public SpawnListeners(RandomSpawnPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(isFirstJoin(player)) {
            player.teleport(plugin.getLocationManager().getRandomLocation(player));
        }
    }

    @Override
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if(!event.isBedSpawn()) {
            event.setRespawnLocation(plugin.getLocationManager().getRandomLocation(event.getPlayer()));
        }
    }

    public boolean isFirstJoin(Player player) {
        final String name = player.getName();
        final File file = new File(player.getWorld().getName() + "/players/" + name + ".dat");
        return !file.exists();
    }

}
