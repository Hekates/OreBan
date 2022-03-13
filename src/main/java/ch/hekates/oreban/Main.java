package ch.hekates.oreban;

import ch.hekates.oreban.commands.OreBanCommand;
import me.kodysimpson.simpapi.command.CommandList;
import me.kodysimpson.simpapi.command.CommandManager;
import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        plugin = this;

        try {
            CommandManager.createCoreCommand(this, "oreban", "(Un-)Bans players from minig ores.", "/oreban <ban|unban|reload>", new CommandList() {
                @Override
                public void displayCommandList(CommandSender sender, List<SubCommand> subCommandList) {

                    sender.sendMessage("--------------------------------");
                    for (SubCommand subCommand : subCommandList){
                        sender.sendMessage(subCommand.getSyntax() + " - " + subCommand.getDescription());
                    }
                    sender.sendMessage("--------------------------------");
                }
            }, OreBanCommand.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getPlugin() {
        return plugin;
    }

}
