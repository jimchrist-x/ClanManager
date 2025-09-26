import org.apache.commons.cli.*;
public class ClanManager {
    public static void main(String[] args) {
        ClanData clashRoyale;
        Arguments  arguments = new Arguments(args);
        CommandLine cmd=arguments.getCmd();
        if (cmd.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("ClanManager", arguments.getOptions());
            System.exit(0);
        }
        if (cmd.hasOption("key")) {
            clashRoyale = new ClanData("%23QRLRPGG8", cmd.getOptionValue("k"));
            if (cmd.hasOption("module")) {
                switch (cmd.getOptionValue("module")) {
                    case "claninfo":
                        ClanInfo clanInfo = new ClanInfo(clashRoyale);
                        // Example output
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
