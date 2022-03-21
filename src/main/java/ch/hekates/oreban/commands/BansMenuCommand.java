package ch.hekates.oreban.commands;

import ch.hekates.oreban.menus.MainMenu;
import me.kodysimpson.simpapi.command.SubCommand;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.MenuManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BansMenuCommand extends SubCommand {
    @Override
    public String getName() {
        return "menu";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Opens up the OreBan menu.";
    }

    @Override
    public String getSyntax() {
        return "/oreban menu";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {

        try {
            MenuManager.openMenu(MainMenu.class,(Player) sender);
        } catch (MenuManagerException e) {
            e.printStackTrace();
        } catch (MenuManagerNotSetupException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}
