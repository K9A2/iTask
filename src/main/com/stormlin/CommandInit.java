package main.com.stormlin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class CommandInit implements ICommand {

    public void usage() {
        System.out.println("Command Init creates an empty list. Takes no argument.");
    }

    public void execute(String[] args) {
        // This command takes no argument.
        if (args.length > 1) {
            this.usage();
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
                System.out.println("Unable to create the list in this folder!");
                return;
            }
            if (!list.createNewFile()) {
                System.out.println("Unable to create the list in this folder!");
                return;
            }

            // Create an empty list object
            TodoList todoList = new TodoList();
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(Const.DEFAULT_FILE_PATH));
            stream.writeObject(todoList);
            stream.close();
            System.out.println("Successfully initialized an empty list in folder: .todo");
        } catch (Exception e) {
            System.out.println("Unable to create the list in this folder!");
            e.printStackTrace();
        }
    }

}