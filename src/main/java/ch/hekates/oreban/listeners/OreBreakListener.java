package ch.hekates.oreban.listeners;

import ch.hekates.oreban.Main;
import ch.hekates.oreban.OreBreakEffect;
import ch.hekates.oreban.utils.OreList;
import ch.hekates.oreban.utils.StorageUtil;
import org.bukkit.block.Block;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.List;

public class OreBreakListener implements Listener {

    @EventHandler
    public void onOreBreak(BlockBreakEvent event){
        Configuration config = Main.getPlugin(Main.class).getConfig();

        Player player = event.getPlayer();
        Block block = event.getBlock();

        //if (player.getGameMode() == GameMode.CREATIVE) return;
        //if (player.hasPermission("oreban.except")) return;
        if (!StorageUtil.contains(player.getUniqueId())) return;

        List ores = OreList.getOres();
        String materialString = block.getBlockData().getMaterial().toString();

        if (ores.contains(materialString)){
            event.setCancelled(true);
            OreBreakEffect.play(player, block.getLocation().add(0.5, 0.5, 0.5), false);
        }
}    }

