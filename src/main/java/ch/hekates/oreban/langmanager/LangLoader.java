package ch.hekates.oreban.langmanager;

import ch.hekates.oreban.Main;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
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
            try {
                log.info(Text.get("console.lang.file_exists"));
            } catch (IOException e) {}
        } else {
            try {
                log.warning(Text.get("console.lang.no_file_exists"));
            } catch (IOException e) {}
            Main.getPlugin().saveResource("lang\\" + language + ".json", false);
        }
    }
}
