package com.stormlin.command;

import com.stormlin.entity.Task;
import com.stormlin.entity.TodoList;
import com.stormlin.util.Const;
import com.stormlin.util.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class CommandStatus implements ICommand {

    public void usage() {
        System.out.println("Use command: status to print the status of current list.");
        System.out.println("usage: status");
        System.out.println("   or: status [-h|--help]");
    }

    public void execute(String[] args) {
        if (args.length > 2) {
            System.out.println("Too many arguments for command: status");
            usage();
            return;
        }
        if (args.length == 2 && (args[1].equals("-h") || args[1].equals("--help"))) {
            usage();
            return;
        }
        TodoList todoList = Util.readFromListFile(Const.DEFAULT_FILE_PATH);
        if (todoList == null) {
            System.out.println("Unable to load this file");
            return;
        }
        if (todoList.getAllBranches().size() == 0) {
            System.out.println("There is no branch yet.");
            return;
        }
        System.out.println(String.format("On branch: %s", todoList.getCurrentBranch().getName()));
        System.out.println();

        HashMap<String, Task> taskMap = todoList.getCurrentBranch().getTaskList();
        ArrayList<String> todoKeyList = new ArrayList<>(taskMap.keySet());
        // Sort tasks by date ascending.
        todoKeyList.sort(Comparator.naturalOrder());
        Iterator<String> iterator = todoKeyList.iterator();

        ArrayList<Task> highPriorityTaskList = new ArrayList<>();
        ArrayList<Task> mediumPriorityTaskList = new ArrayList<>();
        ArrayList<Task> lowPriorityTaskList = new ArrayList<>();

        while (iterator.hasNext()) {
            String key = iterator.next();
            switch (taskMap.get(key).getPriority()) {
            case "high":
                highPriorityTaskList.add(taskMap.get(key));
                break;
            case "medium":
                mediumPriorityTaskList.add(taskMap.get(key));
                break;
            case "low":
                lowPriorityTaskList.add(taskMap.get(key));
                break;
            default:
                break;
            }
        }

        if (highPriorityTaskList.size() == 0 && mediumPriorityTaskList.size() == 0 && lowPriorityTaskList.size() == 0) {
            System.out.println("There are not tasks.");
            return;
        }

        printTaskList(highPriorityTaskList, "high");
        printTaskList(mediumPriorityTaskList, "medium");
        printTaskList(lowPriorityTaskList, "low");
    }

    private void printTaskList(ArrayList<Task> taskList, String priority) {
        if (taskList.size() != 0) {
            System.out.println(String.format("%s %s priority tasks:", taskList.size(), priority));
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Task t : taskList) {
                System.out
                        .println(String.format("  %s | %s | %s", t.getId(), parser.format(t.getDate()), t.getTitle()));
            }
            System.out.println();
        }
    }
}
