package ch.hekates.oreban.menus;

import ch.hekates.oreban.Main;
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
import org.bukkit.NamespacedKey;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class UnOreBanMenu extends Menu {

    public UnOreBanMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Chose a Player to Un-OreBan";
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

        switch (e.getCurrentItem().getType()) {
            case PLAYER_HEAD:
                PersistentDataContainer container = e.getCurrentItem().getItemMeta().getPersistentDataContainer();
                String oreBanID = container.get(new NamespacedKey(Main.getPlugin(), "oreBanID"), PersistentDataType.STRING);

                playerMenuUtility.setData("oreBanID", oreBanID);

                MenuManager.openMenu(ConfirmUnban.class, playerMenuUtility.getOwner());
                break;
            case BARRIER:
                MenuManager.openMenu(MainMenu.class, playerMenuUtility.getOwner());
                break;
        }
    }

    @Override
    public void setMenuItems() {

        List<BannedPlayersInfos> infos = StorageUtil.findAllBans();

        for (BannedPlayersInfos info : infos){

            ItemStack item = SkullCreator.itemFromUuid(info.getPlayerUUID());
            ItemMeta meta = item.getItemMeta();

            meta.getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(), "oreBanID"), PersistentDataType.STRING, info.getPlayerUUID().toString());

            meta.setDisplayName(info.getPlayerName());

            List<String> lore = new ArrayList<>();
            lore.add(info.getNote());
            lore.add(ChatColor.GRAY + info.getPlayerUUID().toString());
            lore.add(ChatColor.AQUA + "OreBanned by: " + ChatColor.YELLOW + info.getBanner());
            meta.setLore(lore);

            item.setItemMeta(meta);
            inventory.addItem(item);
        }


        ItemStack close = makeItem(Material.BARRIER, ChatColor.RED + "Close");

        inventory.setItem(49, close);

    }
}
