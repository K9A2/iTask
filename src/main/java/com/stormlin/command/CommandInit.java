package com.stormlin.command;

import com.stormlin.util.Const;
import com.stormlin.entity.TodoList;
import com.stormlin.util.Util;

import java.io.File;

public class CommandInit implements ICommand {

    public void usage() {
        System.out.println("Command init initialize an empty list in the hidden folder .todo/.\n");
        System.out.println("usage: init");
        System.out.println("   or: init [-h|--help]");
    }

    public void execute(String[] args) {
        // This command takes no argument.
        if (!(args.length != 1 || args.length != 2)) {
            System.out.println("Wrong number of arguments!\n");
            usage();
            return;
        }
        if (args.length == 2) {
            if (Const.validInputOfHelp.contains(args[1])) {
                // Print help message
                usage();
                return;
            }
            System.out.println(String.format("Wrong argument %s\n", args[1]));
            usage();
            return;
        }
        try {
            // Create the folder
            File dir = new File(Const.DEFAULT_FOLDER_PATH);
            if (dir.exists()) {
                System.out.println("Target folder is not empty!");
                return;
            }
            if (!dir.mkdir()) {
                System.out.println("Unable to create the list in this folder!");
                return;
            }

            // Create the list file
            File list = new File(Const.DEFAULT_FILE_PATH);
            if (list.exists()) {
                System.out.println("List file exists!");
                return;
            }
            if (!list.createNewFile()) {
                System.out.println("Unable to create the list in this folder!");
                return;
            }

            // Create an empty list
            TodoList todoList = new TodoList();
            // Serialize this list to file
            Util.saveToListFile(todoList);
            System.out.println("Successfully initialized an empty list in folder: .todo");
        } catch (Exception e) {
            System.out.println("Unable to create the list in this folder!");
            e.printStackTrace();
        }
    }

}