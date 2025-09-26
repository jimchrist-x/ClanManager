import org.apache.commons.cli.*;

public class Arguments {
    private Options options;
    private CommandLineParser parser;
    private CommandLine cmd;
    public Arguments(String[] args) {
        options=new Options();
        Option module = Option.builder("i").argName("module").longOpt("module")
                .desc("Modules include: claninfo").hasArg().build();
        Option apiKey = Option.builder("k").argName("key")
                .longOpt("key").desc("Clash Royale API key").hasArg().build();
        options.addOption("h", "help",false, "Help page");
        options.addOption(apiKey);
        options.addOption(module);
        parser = new DefaultParser();
        try {
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
