package ch.hekates.oreban.commands;

import ch.hekates.oreban.utils.StorageUtil;
import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class UnbanCommand extends SubCommand {
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
        return "Unbans a player from mining ores.";
    }

    @Override
    public String getSyntax() {
        return "/oreban unban <player|uuid>";
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
