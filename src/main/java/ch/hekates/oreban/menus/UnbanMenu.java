package ch.hekates.oreban.menus;

import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class UnbanMenu extends Menu {

    public UnbanMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }


    @Override
    public String getMenuName() {
        return "Are you shure to ore-unban " + target.getName();
    }

    @Override
    public int getSlots() {
        return 8;
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


        ItemStack confirm = makeItem(Material.LIME_CONCRETE, ChatColor.GREEN + "Confirm!", "Confirm that " + target.getName() + " should be ore-unbanned.");
        ItemStack cancel = makeItem(Material.RED_CONCRETE, ChatColor.RED + "Cancel", "Cancel " + target.getName() + "'s ore-unban.");

        inventory.setItem(3, confirm);
        inventory.setItem(5, cancel);

        setFillerGlass();

    }
}
