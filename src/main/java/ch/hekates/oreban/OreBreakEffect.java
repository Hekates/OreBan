package ch.hekates.oreban;

import org.bukkit.*;
import org.bukkit.entity.Player;

public class OreBreakEffect {
    public static void play(Player player, Location blockLocation, Boolean item) {
        if (!item){
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1F);
        player.spawnParticle(Particle.REDSTONE, blockLocation.add(0.5, 0, 0), 10, dustOptions);
        player.spawnParticle(Particle.REDSTONE, blockLocation.add(-0.5, 0.5, 0), 10, dustOptions);
        player.spawnParticle(Particle.REDSTONE, blockLocation.add(0, -0.5, 0.5), 10, dustOptions);
        player.spawnParticle(Particle.REDSTONE, blockLocation.add(-0.5, 0, -0.5), 10, dustOptions);
        player.spawnParticle(Particle.REDSTONE, blockLocation.add(0.5, -0.5, 0), 10, dustOptions);
        player.spawnParticle(Particle.REDSTONE, blockLocation.add(0, 0.5, -0.5), 10, dustOptions);
        player.playSound(blockLocation, Sound.ENTITY_VILLAGER_NO, 10, 1);
        } else {
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1F);
            player.spawnParticle(Particle.REDSTONE, blockLocation.add(0, 0, 0), 1, dustOptions);
            player.playSound(blockLocation, Sound.ENTITY_VILLAGER_NO, 10, 1);

        }
    }
}
