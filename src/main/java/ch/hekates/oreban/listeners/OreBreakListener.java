package ch.hekates.oreban.listeners;

import ch.hekates.oreban.Main;
import ch.hekates.oreban.models.BannedPlayersInfos;
import ch.hekates.oreban.utils.StorageUtil;
import org.bukkit.GameMode;
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

        if (player.getGameMode() == GameMode.CREATIVE) return;
        //if (player.hasPermission("oreban.except")) return;

        player.sendMessage("abjebaut");
        List<BannedPlayersInfos> infos = StorageUtil.findAllBans();

        if (!StorageUtil.contains(player.getUniqueId())) return;
        player.sendMessage("Jebannt");

        List<String> overworldOres = (List<String>) config.getList("overworld-ores");
        List<String> possibleOres = overworldOres;

        if (config.getBoolean("translate-to-deepslate") == true) {
            List<String> deepslateOres = null;
            for (String overworldOre : overworldOres) {
                String deepslateOre = "DEEPSLATE_" + overworldOre;
                deepslateOres.add(deepslateOre);
            }
            possibleOres.addAll(deepslateOres);
        }

        possibleOres.addAll( (List<String>) config.getList("nether-ores"));


        if (config.getBoolean("include-raw-blocks") == true){
            List<String> rawVariants = (List<String>) config.getList("raw-variants");
            List<String> rawBlocks = null;
            for (String rawVariant : rawVariants) {
                String rawBlock = rawVariant + "_BLOCK";
                rawBlocks.add(rawBlock);
            }
            possibleOres.addAll(rawBlocks);

        }

        List<String> additionalBlocks = (List<String>) config.getList("additional-blocks");
        possibleOres.addAll(additionalBlocks);

        String materialString = block.getBlockData().getMaterial().toString();
        for (int i = 0; i < possibleOres.size() ; i++) {
            player.sendMessage(possibleOres.get(i));
        }
        if (possibleOres.contains(materialString)){

            event.setCancelled(true);
            player.sendMessage(materialString);

        }

    }


}
