package ch.hekates.oreban;

import ch.hekates.oreban.commands.BansMenuCommand;
import ch.hekates.oreban.commands.OreBanCommand;
import ch.hekates.oreban.commands.ReloadCommand;
import ch.hekates.oreban.langmanager.LangLoader;
import ch.hekates.oreban.langmanager.Text;
import ch.hekates.oreban.listeners.OreBreakListener;
import ch.hekates.oreban.listeners.OrePickupEvent;
import ch.hekates.oreban.utils.OreList;
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
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        plugin = this;

        Logger log = getLogger();

        String language = getConfig().getString("language");
        LangLoader.load(language);
        log.info("Loaded language: " + language);

        OreList.setOres(OreList.combineOres());
        try {
            log.info(Text.get("console.ore.confirm"));
        } catch (IOException e) {
            log.warning("Text \"console.ore.confirm\" couldn't be loaded in the proper language due to an IO exception!");
            e.printStackTrace();
            log.info("\"console.ore.confirm\"");
        }

        OreList.setItems(OreList.combinedItems());
        log.info("Set checked items!");

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
            }, OreBanCommand.class, BansMenuCommand.class, ReloadCommand.class);
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
        Bukkit.getPluginManager().registerEvents(new OrePickupEvent(), this);

    }

    public static Main getPlugin() {
        return plugin;
    }

}
