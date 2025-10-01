package com.jimchrist.clanmanager;

import org.apache.commons.cli.*;

public class ClanManager {
    public static void main(String[] args) {
        ClanData clashRoyale;
        Arguments  arguments = new Arguments(args);
        CommandLine cmd=arguments.getCmd();
        if (cmd.hasOption("key")) {
            clashRoyale = new ClanData("%23QRLRPGG8", cmd.getOptionValue("k"));
            boolean hasCsv = cmd.hasOption("csv");
            if (cmd.hasOption("module")) {
                switch (cmd.getOptionValue("module")) {
                    case "claninfo":
                        ClanInfo clanInfo = new ClanInfo(clashRoyale);
                        Csv csvClanInfo = null;
                        Csv csvMembers = null;
                        if (hasCsv) {
                            csvClanInfo = new Csv("claninfo.csv", 'a');
                            csvMembers = new Csv("members.csv", 'w');
                            csvMembers.writeHeaders(new String[]{"Tag", "Nickname", "Role", "Last Seen",
                                    "ExpLevel", "Trophies", "Arena", "Clan Rank", "Prev Clan Rank", "Donations",
                                    "Donations Received", "Clan Chest Points"});
                            csvClanInfo.writeHeaders(new String[]{"Tag", "Name", "Type", "Description",
                                    "Clan Chest Status", "Badge Id", "Clan Score", "Clan War Trophies",
                                    "Required Trophies", "Donations Per Week", "Clan Chest Level",
                                    "Clan Chest Max Level", "Members"});
                            csvClanInfo.writeLine(new String[]{clanInfo.getTag(), clanInfo.getName(),
                                    clanInfo.getType(), clanInfo.getDescription(), clanInfo.getClanChestStatus(),
                                    String.valueOf(clanInfo.getBadgeId()), String.valueOf(clanInfo.getClanScore()),
                                    String.valueOf(clanInfo.getClanWarTrophies()),String.valueOf(clanInfo.getRequiredTrophies()),
                                    String.valueOf(clanInfo.getDonationsPerWeek()), String.valueOf(clanInfo.getClanChestLevel()),
                                    String.valueOf(clanInfo.getClanChestMaxLevel()), String.valueOf(clanInfo.getMembers())});
                        }

                        for (int i=0;i<clanInfo.getMembers();i++) {
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
                            if (hasCsv) csvMembers.writeLine(new String[]{clanInfo.getMember(i).getTag(),
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
                        if (hasCsv) {
                            csvClanInfo.close();
                            csvMembers.close();
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
