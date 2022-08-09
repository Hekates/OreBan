package ch.hekates.oreban.listeners;

import ch.hekates.oreban.Main;
import ch.hekates.oreban.OreBreakEffect;
import ch.hekates.oreban.langmanager.Text;
import ch.hekates.oreban.utils.OreList;
import ch.hekates.oreban.utils.StorageUtil;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class OrePickupEvent implements Listener {

    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {

        Logger log = Main.getPlugin().getLogger();

        Configuration config = Main.getPlugin().getConfig();

        //Checks basic conditions
        if (config.getBoolean("allow-item-pickup")) return;
        if (event.getEntity().getType() != EntityType.PLAYER) return;

        Player player = (Player) event.getEntity();
        String item = event.getItem().getItemStack().getType().toString();

        //if (player.getGameMode() == GameMode.CREATIVE) return;
        //if (player.hasPermission("oreban.except")) return;
        if (!StorageUtil.contains(player.getUniqueId())) return;    //Checks if the player is banned

        List items = OreList.getItems();    //List of items specified in the config
        if (items.contains(item)) {
            event.setCancelled(true);

            //Logging
            try {
                log.warning(Text.get("console.event.pickup").replaceAll("%p", player.getDisplayName()));
            } catch (IOException e) {
                log.warning("console.event.pickup -> " + player);
            }

            //Notifying the player
            OreBreakEffect.play(player, event.getItem().getLocation().add(0, 0.3, 0), true);
            try {
                player.sendMessage(Text.get("event.pickup.player").replaceAll("%i", item));

            } catch (IOException e) {
                player.sendMessage(ChatColor.RED + "\"event.pickup.player\"");
                log.warning("Text \"event.pickup.player\" couldn't be loaded in the proper language due to an IO exception!");
                e.printStackTrace();
            }
        }
    }
}
