package ch.hekates.oreban.menus;

import ch.hekates.oreban.utils.StorageUtil;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.MenuManager;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ConfirmUnban extends Menu {

    public ConfirmUnban(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Confirm: Un-OreBan " + StorageUtil.findInformations(playerMenuUtility.getData("oreBanID").toString()).getPlayerName();
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws MenuManagerNotSetupException, MenuManagerException {

        switch (e.getCurrentItem().getType()){
            case RED_CONCRETE:
                MenuManager.openMenu(UnOreBanMenu.class, playerMenuUtility.getOwner());
                break;

            case LIME_CONCRETE:
                UUID oreBanID = UUID.fromString(playerMenuUtility.getData("oreBanID").toString());
                try{
                    playerMenuUtility.getOwner().sendMessage("Successfully un-orebanned " + Bukkit.getPlayer(oreBanID).getName());
                }catch (NullPointerException exception){
                    String savedName = StorageUtil.findInformations(oreBanID).getPlayerName();
                    playerMenuUtility.getOwner().sendMessage("Successfully un-orebanned " + savedName);
                }
                StorageUtil.deleteInformations(oreBanID);
                playerMenuUtility.getOwner().closeInventory();
                break;

        }

    }

    @Override
    public void setMenuItems() {

        ItemStack yes = makeItem(Material.LIME_CONCRETE, ChatColor.GREEN + "Un-OreBan " + StorageUtil.findInformations(playerMenuUtility.getData("oreBanID").toString()).getPlayerName());
        ItemStack no = makeItem(Material.RED_CONCRETE, ChatColor.RED + "Cancel");

        inventory.setItem(3, yes);
        inventory.setItem(5, no);

        setFillerGlass();


    }
}
