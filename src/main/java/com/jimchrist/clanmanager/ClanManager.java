package com.jimchrist.clanmanager;
import com.opencsv.CSVWriter;
import org.apache.commons.cli.*;
public class ClanManager {
    public static void main(String[] args) {
        ClanData clashRoyale;
        Arguments  arguments = new Arguments(args);
        CommandLine cmd=arguments.getCmd();
        if (cmd.hasOption("key")) {
            clashRoyale = new ClanData("%23QRLRPGG8", cmd.getOptionValue("k"));
            if (cmd.hasOption("module")) {
                switch (cmd.getOptionValue("module")) {
                    case "claninfo":
                        ClanInfo clanInfo = new ClanInfo(clashRoyale);
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
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
