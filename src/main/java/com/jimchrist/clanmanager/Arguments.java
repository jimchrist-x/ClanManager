package com.jimchrist.clanmanager;

import org.apache.commons.cli.*;

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
        helpOnly.addOption("h", "help",false, "Help page");
        options.addOption(apiKey);
        options.addOption(module);
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
    public CommandLine getCmd() {
        return cmd;
    }
    public Options getOptions() {
        return options;
    }
}
