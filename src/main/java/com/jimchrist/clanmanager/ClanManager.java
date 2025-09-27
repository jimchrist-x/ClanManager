package com.jimchrist.clanmanager;

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
                            System.out.println(clanInfo.getMember(i).getName());
                        }
                        break;
                    default:
                        break;
                }
            }
        }

    }
}
