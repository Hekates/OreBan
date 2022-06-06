package ch.hekates.oreban;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class OreBreakEffect {
    public static void play(Player player, Location blockLocation){
        player.spawnParticle(Particle.REDSTONE, blockLocation, 10, Color.RED);
        player.playSound(blockLocation, Sound.ENTITY_VILLAGER_NO, 10, 0);
    }
}
