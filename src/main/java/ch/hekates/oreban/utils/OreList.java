package ch.hekates.oreban.utils;

import ch.hekates.oreban.Main;
import org.bukkit.configuration.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OreList {
    public static List combineOres() {
        Configuration config = Main.getPlugin().getConfig();

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

        possibleOres.addAll((List<String>) config.getList("nether-ores"));

        if (config.getBoolean("include-raw-blocks") == true) {
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

        return possibleOres;
    }

    public static List combinedItems() {
        Configuration config = Main.getPlugin().getConfig();
        List<String> possibleItems = getOres();

        System.out.println(possibleItems);

        if (config.getBoolean("include-raw-variants")){
            possibleItems.addAll((Collection<? extends String>) config.getList("raw-variants"));
        }

        System.out.println(possibleItems);

        possibleItems.addAll((Collection<? extends String>) config.getList("processed-variants"));

        System.out.println(possibleItems);

        possibleItems.addAll((Collection<? extends String>) config.getList("additional-blocks"));

        System.out.println(possibleItems);

        possibleItems.addAll((Collection<? extends String>) config.getList("additional-items"));

        System.out.println(possibleItems);

        return possibleItems;
    }


    private static List<String> ores;
    private static List<String> items;

    public static List<String> getOres() {
        return ores;
    }

    public static void setOres(List<String> ores) {
        OreList.ores = ores;
    }

    public static List<String> getItems() {
        return items;
    }

    public static void setItems(List<String> items) {
        OreList.items = items;
    }
}
