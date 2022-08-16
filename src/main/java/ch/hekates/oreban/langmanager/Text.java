package ch.hekates.oreban.langmanager;

import ch.hekates.oreban.Main;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;

public class Text {

    public static String get(String path) throws IOException {
        String language = Main.getPlugin().getConfig().getString("language");

        Gson gson = new Gson();
        File file = new File(Main.getPlugin().getDataFolder().getAbsolutePath() + "/lang/" + language + ".json");

        Reader reader = Files.newBufferedReader(file.toPath());

        Map<?, ?> langMap = gson.fromJson(reader, Map.class);

        String consoleOreConfirm = (String) langMap.get("console.ore.confirm");

        return (String) langMap.get(path);
    }

    public Text() throws IOException {
    }
}
