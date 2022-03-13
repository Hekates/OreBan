package ch.hekates.oreban.utils;

import ch.hekates.oreban.Main;
import ch.hekates.oreban.models.BannedPlayersInfos;
import com.google.gson.Gson;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class StorageUtil {

    private static ArrayList<BannedPlayersInfos> infos = new ArrayList<>();

    public static BannedPlayersInfos saveInformations(Player player, String note){

        BannedPlayersInfos info = new BannedPlayersInfos(
                player.getName(),
                player.getUniqueId(),
                true,
                new Date(),
                note);
        infos.add(info);

        try {
            saveInfos();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return info;
    }
    public static BannedPlayersInfos findInformationsUUID(UUID uuid){

        for (BannedPlayersInfos info : infos){
            if (info.getPlayerUUID().equals(uuid)){
                return info;
            }
        }
        return null;
    }

    public static BannedPlayersInfos findInformationsName(String playerName){

        for (BannedPlayersInfos info : infos){
            if (info.getPlayerName().equals(playerName)){
                return info;
            }
        }
        return null;
    }

    public static void deleteInformationsUUID(UUID uuid){

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

    public static void deleteInformationsName(String name){

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

    public static BannedPlayersInfos updateInfosUUID(UUID uuid, BannedPlayersInfos newInfos){

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
    public static BannedPlayersInfos updateInfosName(String name, BannedPlayersInfos newInfos){

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
        System.out.println("OreBan-Informations saved.");

    }
}
