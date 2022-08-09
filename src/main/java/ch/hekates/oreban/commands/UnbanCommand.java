package ch.hekates.oreban.commands;

import ch.hekates.oreban.Main;
import ch.hekates.oreban.langmanager.Text;
import ch.hekates.oreban.utils.StorageUtil;
import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UnbanCommand extends SubCommand {

    Logger log = Main.getPlugin().getLogger();

    @Override
    public String getName() {
        return "unban";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        try {
            return Text.get("command.description.unban");
        } catch (IOException e) {
            log.warning("Text \"command.description.unban\" couldn't be loaded in the proper language due to an IO exception!");
            e.printStackTrace();
            return "\"command.description.unban\"";
        }
    }

    @Override
    public String getSyntax() {

        try {
            return Text.get("command.syntax.unban");
        } catch (IOException e) {
            log.warning("Text \"command.syntax.unban\" couldn't be loaded in the proper language due to an IO exception!");
            e.printStackTrace();
            return "\"command.syntax.unban\"";
        }
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        StorageUtil.deleteInformations(args[1]);
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        List<String> playerNames = new ArrayList<>();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            playerNames.add(onlinePlayer.getName());
        }
        return playerNames;
    }
}
