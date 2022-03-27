package ch.hekates.oreban.commands;

import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ReloadCommand extends SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Reloads the config.yml and/ or baninformations.json files.";
    }

    @Override
    public String getSyntax() {
        return "/oreban reload [config|baninformations|all]";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {

        if (args[1] == "config"){
            reloadConfig();
        } else if (args[1] == "informations"){
            reloadInfos();
        } else if (args[1] == "all"){
            reloadConfig();
            reloadInfos();
        }

    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        List<String> arguments = new ArrayList<>();
        arguments.add("config");
        arguments.add("baninformations");
        arguments.add("all");
        return arguments;
    }

    static void reloadConfig(){
        System.out.println("config");
    }
    static void reloadInfos(){
        System.out.println("infos");
    }
}
