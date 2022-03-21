package ch.hekates.oreban.models;

import java.util.Date;
import java.util.UUID;

public class BannedPlayersInfos {

    private String playerName;
    private UUID playerUUID;
    private Boolean oreBanned;
    private Date dateBanned;
    private String note;
    private String banner;

    public BannedPlayersInfos(String playerName, UUID playerUUID, Boolean oreBanned, Date dateBanned, String note, String banner) {
        this.playerUUID = playerUUID;
        this.playerName = playerName;
        this.oreBanned = oreBanned;
        this.dateBanned = dateBanned;
        this.note = note;
        this.banner = banner;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Boolean isOreBanned() {
        return oreBanned;
    }

    public void setOreBanned(Boolean oreBanned) {
        this.oreBanned = oreBanned;
    }

    public Date getDateBanned() {
        return dateBanned;
    }

    public void setDateBanned(Date dateBanned) {
        this.dateBanned = dateBanned;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner){
        this.banner = banner;
    }
}
