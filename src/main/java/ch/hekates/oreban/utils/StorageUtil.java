package ch.hekates.oreban.utils;

import ch.hekates.oreban.Main;
import ch.hekates.oreban.models.BannedPlayersInfos;
import com.google.gson.Gson;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class StorageUtil {
    static Logger log = Main.getPlugin().getLogger();

    private static ArrayList<BannedPlayersInfos> infos = new ArrayList<>();

    public static BannedPlayersInfos saveInformations(Player player, String note, Player banner){

        BannedPlayersInfos info = new BannedPlayersInfos(
                player.getName(),
                player.getUniqueId(),
                true,
                new Date(),
                note,
                banner.getDisplayName());
        infos.add(info);

        try {
            saveInfos();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return info;
    }

    public static boolean contains(UUID uuid){
        for (BannedPlayersInfos info : infos){
            if (info.getPlayerUUID().equals(uuid)){
                return true;
            }
        }
        return false;
    }

    public static BannedPlayersInfos findInformations(UUID uuid){

        for (BannedPlayersInfos info : infos){
            if (info.getPlayerUUID().equals(uuid)){
                return info;
            }
        }
        return null;
    }

    public static BannedPlayersInfos findInformations(String playerName){

        for (BannedPlayersInfos info : infos){
            if (info.getPlayerName().equals(playerName)){
                return info;
            }
        }
        return null;
    }

    public static void deleteInformations(UUID uuid){

        for (BannedPlayersInfos info : infos){
            if (info.getPlayerUUID().equals(uuid)){
                infos.remove(info);
                break;
            }
        }
        try {
            saveInfos();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteInformations(String name){

        for (BannedPlayersInfos info : infos){
            if (info.getPlayerName().equals(name)){
                infos.remove(info);
                break;
            }
        }
        try {
            saveInfos();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BannedPlayersInfos updateInfos(UUID uuid, BannedPlayersInfos newInfos){

        for (BannedPlayersInfos info : infos) {
            if (info.getPlayerUUID().equals(uuid)) {
                info.setPlayerName(newInfos.getPlayerName());
                info.setNote(newInfos.getNote());

                try {
                    saveInfos();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return info;
            }
        }
        return null;
    }
    public static BannedPlayersInfos updateInfos(String name, BannedPlayersInfos newInfos){

        for (BannedPlayersInfos info : infos) {
            if (info.getPlayerName().equals(name)) {
                info.setPlayerName(newInfos.getPlayerName());
                info.setNote(newInfos.getNote());

                try {
                    saveInfos();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return info;
            }
        }
        return null;
    }

    public static List<BannedPlayersInfos> findAllBans(){
        return infos;
    }


    public static void saveInfos() throws IOException {
        Gson gson = new Gson();
        File file = new File(Main.getPlugin().getDataFolder().getAbsolutePath() + "/informations.json");
        file.getParentFile().mkdir();
        file.createNewFile();
        Writer writer = new FileWriter(file, false);
        gson.toJson(infos, writer);
        writer.flush();
        writer.close();
        log.info("OreBan-Informations saved.");

    }

    public static void loadOreBans() throws IOException{

        Gson gson = new Gson();
        File file = new File(Main.getPlugin().getDataFolder().getAbsolutePath() + "/informations.json");
        if (file.exists()){
            Reader reader = new FileReader(file);
            BannedPlayersInfos[] b = gson.fromJson(reader, BannedPlayersInfos[].class);
            infos = new ArrayList<>(Arrays.asList(b));
            log.info("OreBan-Informations loaded.");
        }

    }
}
