package ch.hekates.oreban.listeners;

import ch.hekates.oreban.Main;
import ch.hekates.oreban.OreBreakEffect;
import ch.hekates.oreban.utils.OreList;
import ch.hekates.oreban.utils.StorageUtil;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import java.util.List;

public class OrePickupEvent implements Listener {

    @EventHandler
    public void onPickup(EntityPickupItemEvent event){
        Configuration config = Main.getPlugin().getConfig();

        if (config.getBoolean("allow-item-pickup")) return;
        if (event.getEntity().getType() != EntityType.PLAYER) return;

        Player player = (Player) event.getEntity();
        String item = event.getItem().getItemStack().getType().toString();

        if (player.hasPermission("oreban.except")) return;
        if (!StorageUtil.contains(player.getUniqueId())) return;

        List items = OreList.getItems();
        if (items.contains(item)){
            event.setCancelled(true);
            OreBreakEffect.play(player, event.getItem().getLocation());
        }
    }
}
