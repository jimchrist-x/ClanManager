package com.jimchrist.clanmanager;

import org.apache.commons.cli.*;

import java.io.FileReader;
import java.util.Properties;

public class ClanManager {
    static void printMembers(ClanInfo clanInfo) {
        for (int i = 0; i < clanInfo.getMembers(); i++) {
            System.out.printf("Players tag is %s his/her nickname is %s. He/her is a %s in the clan.\n" +
                            "He/her was last seen at %s. His/her stats are:\nExpLevel: %d\nTrophies: %d\nArena: %s\n" +
                            "His/her clan profile includes:\nClanRank: %d\nPrevClanRank: %d\nDonations: %d\nDonations received: %d\n" +
                            "ClanChestPoints: %d", clanInfo.getMember(i).getTag(), clanInfo.getMember(i).getName(), clanInfo.getMember(i).getRole(),
                    clanInfo.getMember(i).getLastSeen(), clanInfo.getMember(i).getExpLevel(), clanInfo.getMember(i).getTrophies(),
                    clanInfo.getMember(i).getArena().getName(), clanInfo.getMember(i).getClanRank(), clanInfo.getMember(i).getPrevClanRank(),
                    clanInfo.getMember(i).getDonations(), clanInfo.getMember(i).getDonationsReceived(),
                    clanInfo.getMember(i).getClanChestPoints());
            System.out.println();
            System.out.println("===================================================================================");
        }
    }

    static void exportCsv(ClanInfo clanInfo, boolean hasCsv) {
        if (hasCsv) {
            Csv csvClanInfo = new Csv("claninfo.csv", 'a');
            Csv csvMembers = new Csv("members.csv", 'w');
            csvMembers.writeHeaders(new String[]{"Tag", "Nickname", "Role", "Last Seen",
                    "ExpLevel", "Trophies", "Arena", "Clan Rank", "Prev Clan Rank", "Donations",
                    "Donations Received", "Clan Chest Points"});
            csvClanInfo.writeHeaders(new String[]{"Tag", "Name", "Type", "Description",
                    "Clan Chest Status", "Badge Id", "Clan Score", "Clan War Trophies", "Location",
                    "Required Trophies", "Donations Per Week", "Clan Chest Level",
                    "Clan Chest Max Level", "Members"});
            csvClanInfo.writeLine(new String[]{clanInfo.getTag(), clanInfo.getName(),
                    clanInfo.getType(), clanInfo.getDescription(), clanInfo.getClanChestStatus(),
                    String.valueOf(clanInfo.getBadgeId()), String.valueOf(clanInfo.getClanScore()),
                    String.valueOf(clanInfo.getClanWarTrophies()), clanInfo.getLocation().getName(),
                    String.valueOf(clanInfo.getRequiredTrophies()),
                    String.valueOf(clanInfo.getDonationsPerWeek()), String.valueOf(clanInfo.getClanChestLevel()),
                    String.valueOf(clanInfo.getClanChestMaxLevel()), String.valueOf(clanInfo.getMembers())});
            for (int i = 0; i < clanInfo.getMembers(); i++) {
                csvMembers.writeLine(new String[]{clanInfo.getMember(i).getTag(),
                        clanInfo.getMember(i).getName(), clanInfo.getMember(i).getRole(),
                        clanInfo.getMember(i).getLastSeen(), String.valueOf(clanInfo.getMember(i).getExpLevel()),
                        String.valueOf(clanInfo.getMember(i).getTrophies()),
                        clanInfo.getMember(i).getArena().getName(),
                        String.valueOf(clanInfo.getMember(i).getClanRank()),
                        String.valueOf(clanInfo.getMember(i).getPrevClanRank()),
                        String.valueOf(clanInfo.getMember(i).getDonations()),
                        String.valueOf(clanInfo.getMember(i).getDonationsReceived()),
                        String.valueOf(clanInfo.getMember(i).getClanChestPoints())});
            }
            csvClanInfo.close();
            csvMembers.close();
        }
    }

    static void exportSql(ClanInfo clanInfo, boolean hasSql) {
        if (hasSql) {
            String urlOracle = "jdbc:oracle:thin:@localhost:1521:XE", usernameOracle = "clanmanager", passwordOracle = "password123";
            Properties sqlProps = new Properties();
            try (FileReader reader = new FileReader("config/db.properties")) {
                sqlProps.load(reader);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
            urlOracle = sqlProps.getProperty("db.url");
            usernameOracle = sqlProps.getProperty("db.user");
            passwordOracle = sqlProps.getProperty("db.password");
            try {
                DatabaseHandler database = new DatabaseHandler(urlOracle, usernameOracle, passwordOracle);
                database.insertBoth(clanInfo);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) {
        ClanData clashRoyale;
        Arguments arguments = new Arguments(args);
        CommandLine cmd = arguments.getCmd();
        if (cmd.hasOption("key") && cmd.hasOption("tag")) {
            clashRoyale = new ClanData("%23" + cmd.getOptionValue("tag").replace("#", "").toUpperCase(), cmd.getOptionValue("k"));
            boolean hasCsv = cmd.hasOption("csv");
            boolean hasSql = cmd.hasOption("sql");
            if (cmd.hasOption("module")) {
                switch (cmd.getOptionValue("module")) {
                    case "claninfo":
                        ClanInfo clanInfo = new ClanInfo(clashRoyale);
                        printMembers(clanInfo);
                        exportCsv(clanInfo, hasCsv);
                        exportSql(clanInfo, hasSql);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}