package com.jimchrist.clanmanager;

public class ClanData {
    ClashAPI api;
    String clanTag;
    String directory;
    ApiResponse warLog, riverRaceLog, currentWar, clanInfo, members, currentRiverRace;

    public ClanData(String clanTag, String API_KEY) {
        this.clanTag = clanTag;
        api = new ClashAPI(API_KEY);
        directory = "/clans/";
    }

    public void loadWarLog() {
        warLog = api.request(directory + clanTag + "/warlog");
    }

    public void loadRiverRaceLog() {
        riverRaceLog = api.request(directory + clanTag + "/riverracelog");
    }

    public void loadCurrentWar() {
        currentWar = api.request(directory + clanTag + "/currentwar");
    }

    public void loadClanInfo() {
        clanInfo = api.request(directory + clanTag);
    }

    public void loadMembers() {
        members = api.request(directory + clanTag + "/members");
    }

    public void loadCurrentRiverRace() {
        currentRiverRace = api.request(directory + clanTag + "/currentriverrace");
    }

    public void loadAll() {
        loadWarLog();
        loadRiverRaceLog();
        loadCurrentWar();
        loadClanInfo();
        loadMembers();
        loadCurrentRiverRace();
    }

    public ApiResponse getWarLog() {
        return warLog;
    }

    public ApiResponse getRiverRaceLog() {
        return riverRaceLog;
    }

    public ApiResponse getCurrentWar() {
        return currentWar;
    }

    public ApiResponse getClanInfo() {
        return clanInfo;
    }

    public ApiResponse getMembers() {
        return members;
    }

    public ApiResponse getCurrentRiverRace() {
        return currentRiverRace;
    }
}