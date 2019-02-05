package main.com.stormlin;

public class Program {

    private static void usage() {
        System.out.println("Usage");
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify a command");
            Program.usage();
            return;
        }
        String command = args[0];
        switch (command) {
            case "init":
                CommandInit init = new CommandInit();
                init.execute(args);
                break;
            case "status":
                CommandStatus status = new CommandStatus();
                status.execute(args);
                break;
            case "add":
                CommandAdd add = new CommandAdd();
                add.execute(args);
                break;
            default:
                System.out.println("Wrong command.");
                usage();
                break;
        }
    }
}
