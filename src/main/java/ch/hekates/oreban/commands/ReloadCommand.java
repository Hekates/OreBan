package ch.hekates.oreban.commands;

import ch.hekates.oreban.Main;
import ch.hekates.oreban.langmanager.Text;
import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ReloadCommand extends SubCommand {

    Logger log = Main.getPlugin().getLogger();

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

        try {
            return Text.get("command.description.reload");
        } catch (IOException e) {
            log.warning("Text \"command.description.reload\" couldn't be loaded in the proper language due to an IO exception!");
            e.printStackTrace();
            return "\"command.description.reload\"";
        }
    }

    @Override
    public String getSyntax() {

        try {
            return Text.get("command.syntax.reload");
        } catch (IOException e) {
            log.warning("Text \"command.syntax.reload\" couldn't be loaded in the proper language due to an IO exception!");
            e.printStackTrace();
            return "\"command.syntax.reload\"";
        }
    }

    @Override
    public void perform(CommandSender sender, String[] args) {

        if (args[1] == "configurations"){
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
        arguments.add("configurations");
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
