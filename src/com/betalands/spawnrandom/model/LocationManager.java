package com.betalands.spawnrandom.model;

import com.betalands.spawnrandom.RandomSpawnPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class LocationManager {

    private int radius;
    private World world;

    public LocationManager(RandomSpawnPlugin plugin) {
        radius = plugin.getConfig().getRadius();
        world = plugin.getServer().getWorld("world");
    }

    public Location getRandomLocation(Player player) {
        Location loc = getRandomLocation(world);
        while(!isValid(loc)) {
            loc = getRandomLocation(world);
        }
        return loc;
    }

    private Location getRandomLocation(World world) {
        int x = ThreadLocalRandom.current().nextInt(-radius, radius);
        int z = ThreadLocalRandom.current().nextInt(-radius, radius);

        int chunkX = x >> 4; // This just gets the chunk coords at a point using math magic
        int chunkZ = z >> 4;

        if (!world.isChunkLoaded(chunkX, chunkZ)) {
            world.loadChunk(chunkX, chunkZ);
        }

        int y = world.getHighestBlockYAt(x, z);

        return new Location(world, x, y, z);

    }


    private boolean isValid(final Location loc) {
        Material mat = loc.getBlock().getType();
        return Stream.of(Material.LAVA, Material.STATIONARY_LAVA, Material.STATIONARY_WATER, Material.WATER, Material.CACTUS).noneMatch(m -> m == mat);
    }

    public World getWorld() {
        return world;
    }
}
