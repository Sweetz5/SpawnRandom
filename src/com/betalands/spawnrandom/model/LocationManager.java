package com.betalands.spawnrandom.model;

import com.betalands.spawnrandom.RandomSpawnPlugin;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class LocationManager {

    private int radius;

    public LocationManager(RandomSpawnPlugin plugin) {
        radius = plugin.getConfig().getRadius();
    }

    public Location getRandomLocation(Player player) {
        return getRandomLocation(player.getWorld());
    }

    private Location getRandomLocation(World world) {
        int x = ThreadLocalRandom.current().nextInt(-radius, radius);
        int z = ThreadLocalRandom.current().nextInt(-radius, radius);
        int y = world.getHighestBlockYAt(x, z);

        int chunkX = x >> 4; // This just gets the chunk coords at a point using math magic
        int chunkZ = z >> 4;

        if (!world.isChunkLoaded(chunkX, chunkZ)) {
            world.loadChunk(chunkX, chunkZ);
        }

        return new Location(world, x, y, z);

    }
}
