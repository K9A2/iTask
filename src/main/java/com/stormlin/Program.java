package com.stormlin;

import com.stormlin.command.*;

public class Program {

    private static void usage() {
        System.out.println("usage: todo <command> [arguments]");
        System.out.println("");
        System.out.println("All supported commands are:");
        System.out.println("  init    Initialize an empty list in folder ./todo");
        System.out.println("  status  Print all tasks in current list");
        System.out.println("  add     Add a new task to current list");
        System.out.println("  remove  Remove a task from current list");
        System.out.println("  branch  Add, remove or switch list branch");
        System.out.println("");
        System.out.println("Use \"todo <command> -h\" or \"todo <command> --help\" to get help for specific command");
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
        case "remove":
            CommandRemove remove = new CommandRemove();
            remove.execute(args);
            break;
        case "branch":
            CommandBranch branch = new CommandBranch();
            branch.execute(args);
            break;
        case "-h":
            Program.usage();
            break;
        case "--help":
            Program.usage();
            break;
        default:
            System.out.println("Wrong command.");
            usage();
            break;
        }
    }
}
