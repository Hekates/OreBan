package ch.hekates.oreban.listeners;

import ch.hekates.oreban.Main;
import ch.hekates.oreban.models.BannedPlayersInfos;
import ch.hekates.oreban.utils.OreListCombineUtil;
import ch.hekates.oreban.utils.StorageUtil;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

public class OreBreakListener implements Listener {

    @EventHandler
    public void onOreBreak(BlockBreakEvent event){
        Configuration config = Main.getPlugin(Main.class).getConfig();

        Player player = event.getPlayer();
        Block block = event.getBlock();

        //if (player.getGameMode() == GameMode.CREATIVE) return;
        //if (player.hasPermission("oreban.except")) return;

        List<BannedPlayersInfos> infos = StorageUtil.findAllBans();

        if (!StorageUtil.contains(player.getUniqueId())) return;

        List<String> possibleOres = OreListCombineUtil.combinedOres();

        String materialString = block.getBlockData().getMaterial().toString();

        player.sendMessage(String.valueOf(possibleOres));
        if (possibleOres.contains(materialString)){

            event.setCancelled(true);

        }

    }


}
