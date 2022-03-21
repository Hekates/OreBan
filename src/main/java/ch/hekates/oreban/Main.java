package ch.hekates.oreban;

import ch.hekates.oreban.commands.BansMenuCommand;
import ch.hekates.oreban.commands.OreBanCommand;
import ch.hekates.oreban.listeners.OreBreakListener;
import ch.hekates.oreban.utils.StorageUtil;
import me.kodysimpson.simpapi.command.CommandList;
import me.kodysimpson.simpapi.command.CommandManager;
import me.kodysimpson.simpapi.command.SubCommand;
import me.kodysimpson.simpapi.menu.MenuManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
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
            }, OreBanCommand.class, BansMenuCommand.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        MenuManager.setup(getServer(), this);

        try {
            StorageUtil.loadOreBans();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bukkit.getPluginManager().registerEvents(new OreBreakListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getPlugin() {
        return plugin;
    }

}
