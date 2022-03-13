package ch.hekates.oreban.menus;

import ch.hekates.oreban.models.BannedPlayersInfos;
import ch.hekates.oreban.utils.StorageUtil;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.heads.SkullCreator;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.MenuManager;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ListBansMenu extends Menu {

    public ListBansMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "List of all OreBans";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws MenuManagerNotSetupException, MenuManagerException {

        if (e.getCurrentItem().getType() == Material.BARRIER){
            MenuManager.openMenu(MainMenu.class, playerMenuUtility.getOwner());
        }else if (e.getCurrentItem().getType() == Material.PLAYER_HEAD){
            MenuManager.openMenu(UnbanMenu.class, playerMenuUtility.getOwner());
        }
    }

    @Override
    public void setMenuItems() {

        List<BannedPlayersInfos> infos = StorageUtil.findAllBans();

        for (BannedPlayersInfos info : infos){

            ItemStack item = SkullCreator.itemFromUuid(info.getPlayerUUID());
            item.getItemMeta().setDisplayName(info.getPlayerName());
            List<String> lore = new ArrayList<>();
            lore.add(info.getNote());
            lore.add("UUID: " + info.getPlayerUUID().toString());
            lore.add("Ban-date: " + info.getDateBanned());
            item.getItemMeta().setLore(lore);
            inventory.addItem(item);
        }


        ItemStack close = makeItem(Material.BARRIER, ChatColor.RED + "Close");

        inventory.setItem(49, close);

    }
}
