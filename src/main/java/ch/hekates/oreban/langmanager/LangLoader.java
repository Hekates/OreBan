package ch.hekates.oreban.langmanager;

import ch.hekates.oreban.Main;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LangLoader {
    public static void load(String language){
        Plugin plugin = Main.getPlugin();
        Logger log = plugin.getLogger();

        List<File> langFiles = new ArrayList<File>();
        File file = new File(Main.getPlugin().getDataFolder().getAbsolutePath() + "/lang/" + language + ".json");

        if (file.exists()){
            log.finest("YAYYYYYYYYYYYY");
        } else {
            log.warning("NAYYYYYYYYYY");
            Main.getPlugin().saveResource("lang\\" + language + ".json", false);
        }
    }
}
