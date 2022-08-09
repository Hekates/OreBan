package ch.hekates.oreban.listeners;

import ch.hekates.oreban.Main;
import ch.hekates.oreban.OreBreakEffect;
import ch.hekates.oreban.langmanager.Text;
import ch.hekates.oreban.utils.OreList;
import ch.hekates.oreban.utils.StorageUtil;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class OreBreakListener implements Listener {

    @EventHandler
    public void onOreBreak(BlockBreakEvent event) {
        Logger log = Main.getPlugin().getLogger();

        Configuration config = Main.getPlugin(Main.class).getConfig();

        Player player = event.getPlayer();
        Block block = event.getBlock();
        String materialString = block.getBlockData().getMaterial().toString();

        //Checks basic conditions
        //if (player.getGameMode() == GameMode.CREATIVE) return;
        //if (player.hasPermission("oreban.except")) return;
        if (!StorageUtil.contains(player.getUniqueId())) return;

        List ores = OreList.getOres();  //List of blocks specified in the config

        if (ores.contains(materialString)) {
            event.setCancelled(true);

            //Logging
            try {
                log.warning(Text.get("console.event.break").replaceAll("%p", player.getDisplayName()));
            } catch (IOException e) {
                log.warning("console.event.break -> " + player);
            }

            //Notifying the player
            OreBreakEffect.play(player, block.getLocation().add(0.5, 0.5, 0.5), false);
            try {
                player.sendMessage(Text.get("event.break.player").replaceAll("%b", materialString));
            } catch (IOException e) {
                player.sendMessage(ChatColor.RED + "\"event.break.player\"");
                log.warning("Text \"event.break.player\" couldn't be loaded in the proper language due to an IO exception!");
                e.printStackTrace();
            }
        }
    }
}

