package com.jimchrist.clanmanager;

import org.apache.commons.cli.*;
/*
* This class handles the command line arguments
*/
public class Arguments {
    private Options options;
    private CommandLineParser parser;
    private CommandLine cmd;
    public Arguments(String[] args) {
        Options helpOnly = new Options();
        options=new Options();
        parser = new DefaultParser();
        Option module = Option.builder("i").argName("module").longOpt("module")
                .desc("Modules include: claninfo").hasArg().build();
        Option apiKey = Option.builder("k").argName("key")
                .longOpt("key").desc("Clash Royale API key").hasArg().required().build();
        Option csv = Option.builder("c").argName("csv").longOpt("csv").desc("Write to CSV").build();
        Option clanTag = Option.builder("t").argName("tag").longOpt("tag").desc("Clan tag").hasArg().required().build();
        helpOnly.addOption("h", "help",false, "Help page");
        Option sqlDatabase = Option.builder("s").argName("sql").longOpt("sql").desc("Oracle DB").build();
        options.addOption(apiKey);
        options.addOption(module);
        options.addOption(csv);
        options.addOption(clanTag);
        options.addOption(sqlDatabase);
        // We first parse the helpOnly options, then the normal options. If helpOnly is present, we exit.
        try {
            CommandLine helpCmd = parser.parse(helpOnly, args, true);
            if (helpCmd.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ClanManager", options);
                System.exit(0);
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        try {
            cmd = parser.parse(options, args);
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    // Getters
    public CommandLine getCmd() {
        return cmd;
    }
    public Options getOptions() {
        return options;
    }
}
