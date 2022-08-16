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
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class OreBanCommand extends SubCommand {

    Logger log = Main.getPlugin().getLogger();

    @Override
    public String getName() {
        return "ban";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        try {
            return Text.get("command.description.ban");
        } catch (IOException e) {
            log.warning("Text \"command.description.ban\" couldn't be loaded in the proper language due to an IO exception!");
            e.printStackTrace();
            return "\"command.description.ban\"";
        }
    }

    @Override
    public String getSyntax() {

        try {
            return Text.get("command.syntax.ban");
        } catch (IOException e) {
            log.warning("Text \"command.syntax.ban\" couldn't be loaded in the proper language due to an IO exception!");
            e.printStackTrace();
            return "\"command.syntax.ban\"";
        }
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (args.length > 2){
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 2; i < (args.length - 1); i ++){
                stringBuilder.append(args[i]).append(" ");
            }
            stringBuilder.append(args[args.length - 1]);

            StorageUtil.saveInformations(Bukkit.getPlayer(args[1]),
                    stringBuilder.toString() + " [" + new Date().toString() + "]",
                    (Player) sender);
        }else {
            StorageUtil.saveInformations(Bukkit.getPlayer(args[1]),
                    " [" + new Date().toString() + "]",
                    (Player) sender);
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {

        List<String> players = new ArrayList<>();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            players.add(onlinePlayer.getName());
        }

        return players;
    }
}
