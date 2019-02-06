package main.com.stormlin.command;

import main.com.stormlin.util.Const;
import main.com.stormlin.entity.TodoList;
import main.com.stormlin.util.Util;

import java.io.File;

public class CommandInit implements ICommand {

    public void usage() {
        System.out.println("Command Init creates an empty list. Takes no argument.");
    }

    public void execute(String[] args) {
        // This command takes no argument.
        if (args.length > 1) {
            System.out.println("Too many arguments for command: init");
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