package ch.hekates.oreban.menus;

import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MainMenu extends Menu {
    public MainMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "OreBanned Players";
    }

    @Override
    public int getSlots() {
        return 36;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws MenuManagerNotSetupException, MenuManagerException {

    }

    @Override
    public void setMenuItems() {

        ItemStack ban = makeItem(Material.WRITABLE_BOOK, "OreBan a Player");
        ItemStack list = makeItem(Material.FLOWER_BANNER_PATTERN, "List all OreBans");
        ItemStack unban = makeItem(Material.NETHERITE_SWORD, "OreUnban a Player");
        ItemStack close = makeItem(Material.BARRIER, ChatColor.RED + "Close");

        inventory.setItem(11, ban);
        inventory.setItem(13, list);
        inventory.setItem(15, unban);
        inventory.setItem(35, close);

        setFillerGlass();

    }
}
