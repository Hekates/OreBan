package ch.hekates.oreban.utils;

import ch.hekates.oreban.Main;
import org.bukkit.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

public class OreListCombineUtil {
    public static List combinedOres(){
        Configuration config = Main.getPlugin(Main.class).getConfig();

        List<String> overworldOres = (List<String>) config.getList("overworld-ores");
        List<String> possibleOres = overworldOres;

        if (config.getBoolean("translate-to-deepslate") == true) {
            List<String> deepslateOres = new ArrayList<>();
            for (String overworldOre : overworldOres) {
                String deepslateOre = "DEEPSLATE_" + overworldOre;
                deepslateOres.add(deepslateOre);
            }
            possibleOres.addAll(deepslateOres);
        }

        possibleOres.addAll( (List<String>) config.getList("nether-ores"));

        if (config.getBoolean("include-raw-blocks") == true){
            List<String> rawVariants = (List<String>) config.getList("raw-variants");
            List<String> rawBlocks = new ArrayList<>();
            for (String rawVariant : rawVariants) {
                String rawBlock = rawVariant + "_BLOCK";
                rawBlocks.add(rawBlock);
            }
            possibleOres.addAll(rawBlocks);

        }

        List<String> additionalBlocks = (List<String>) config.getList("additional-blocks");
        possibleOres.addAll(additionalBlocks);

        return combinedOres();
    }
}
