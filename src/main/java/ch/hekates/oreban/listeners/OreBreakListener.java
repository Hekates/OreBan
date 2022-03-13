package ch.hekates.oreban.listeners;

import ch.hekates.oreban.Main;
import org.bukkit.block.Block;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OreBreakListener implements Listener {

    @EventHandler
    public void onOreBreak(BlockBreakEvent event){
        Configuration config = Main.getPlugin(Main.class).getConfig();

        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (player.hasPermission("oreban.except")) return;
        config.getConfigurationSection("ores").getKeys(true).forEach(key ->{

        });

    }


}
