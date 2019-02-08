package com.stormlin.command;

import com.stormlin.entity.Branch;
import com.stormlin.entity.TodoList;
import com.stormlin.util.Const;
import com.stormlin.util.Util;

public class CommandAdd implements ICommand {

    /**
     * Use this method to print the usage of command add
     */
    public void usage() {
        System.out.println("Use this command to add new task.");
        System.out.println("This command takes exactly 2 arguments: <title> <priority>.");
        System.out.println("And there are only 3 legal options for task priority (high, medium and low).\n");
        System.out.println("usage: add task-title [high|medium|low]");
        System.out.println("  or : add [-h|--help]");
    }

    /**
     * This method executes the command
     * 
     * @param args The arguments from command line
     */
    public void execute(String[] args) {
        if (!(args.length == 2 || args.length == 3)) {
            System.out.println("Invlid number of arguments.\n");
            usage();
            return;
        }
        if (args.length == 2) {
            if (Const.validInputOfHelp.contains(args[1])) {
                // A valid requiest for help
                usage();
                return;
            } else {
                System.out.println(String.format("Invalid argument: %s.\n", args[1]));
                usage();
                return;
            }
        }

        String title = args[1];
        String priority = args[2];

        if (!Const.validInputOfPriority.contains(priority)) {
            System.out.println(String.format("Illegal input of task priority: %s.\n", priority));
            usage();
            return;
        }

        TodoList todoList = Util.readFromListFile(Const.DEFAULT_FILE_PATH);
        if (todoList == null) {
            System.out.println(String.format("Could not load the list file: %s", Const.DEFAULT_FILE_PATH));
            return;
        }
        Branch currentBranch = todoList.getCurrentBranch();
        if (currentBranch != null) {
            currentBranch.addTask(title, priority);
        }
        Util.saveToListFile(todoList);
    }

}
