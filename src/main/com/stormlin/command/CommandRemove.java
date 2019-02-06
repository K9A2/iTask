package main.com.stormlin.command;

import main.com.stormlin.entity.Task;
import main.com.stormlin.entity.TodoList;
import main.com.stormlin.util.Const;
import main.com.stormlin.util.Util;

import java.util.HashMap;

public class CommandRemove implements ICommand {

    public void usage() {
        System.out.println("Use this command to remove task specified by HEX ID.");
    }

    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println();
            usage();
            return;
        }

        String key = args[1];

        TodoList todoList = Util.readFromListFile(Const.DEFAULT_FILE_PATH);
        if (todoList == null) {
            System.out.println("Unable to read the list file.");
            return;
        }

        HashMap<String, Task> taskMap = todoList.getCurrentBranch().getTaskList();
        if (taskMap.remove(key) == null) {
            System.out.println(String.format("Key: %s does not exists!", key));
        }
        System.out.println(String.format("Key: %s removed successfully!", key));
        Util.saveToListFile(todoList);
    }

}
