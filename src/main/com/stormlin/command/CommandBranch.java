package main.com.stormlin.command;

import main.com.stormlin.entity.Branch;
import main.com.stormlin.entity.TodoList;
import main.com.stormlin.util.Const;
import main.com.stormlin.util.Util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CommandBranch implements ICommand {

    public void usage() {
        System.out.println("Use this command to create, switch and delete branch.");
    }

    public void execute(String[] args) {
        if (args.length > 3) {
            System.out.println("Too many arguments for command: branch.");
            usage();
            return;
        }

        TodoList todoList = Util.readFromListFile(Const.DEFAULT_FILE_PATH);
        if (todoList == null) {
            System.out.println("Unable to read the list from disk!");
            return;
        }

        if (args.length == 1) {
            printAllBranch(todoList);
            return;
        }

        switch (args[1]) {
            case "add":
                addBranch(todoList, args);
                break;
            case "switch":
                switchBranch(todoList, args);
                break;
            case "remove":
                removeBranch(todoList, args);
                break;
            default:
                break;
        }

    }

    private void addBranch(TodoList todoList, String[] args) {
        String key = Util.get8BitIdFromString(args[2]);
        if (todoList.getAllBranches().containsKey(key)) {
            System.out.println(String.format("Branch: %s (%s) already exists!",
                    todoList.getAllBranches().get(key).getName(), key));
            return;
        }
        Branch newBranch = new Branch(args[2]);
        todoList.getAllBranches().put(key, newBranch);
        System.out.println(String.format("Branch: %s (%s) added successfully!", newBranch.getName(), newBranch.getId()));
        if (todoList.getAllBranches().size() == 1) {
            todoList.setCurrentBranch(key);
            System.out.println(String.format("Branch %s is set to the current branch.", key));
        }
        Util.saveToListFile(todoList);
    }

    private void switchBranch(TodoList todoList, String[] args) {
        HashMap<String, Branch> branches = todoList.getAllBranches();
        String key = args[2];
        if (!branches.containsKey(key)) {
            System.out.println(String.format("Can not switch to branch: %s", key));
            return;
        }
        todoList.setCurrentBranch(key);
        System.out.println(String.format("Switch to branch: %s", key));
    }

    private void removeBranch(TodoList todoList, String[] args) {
        String key = args[2];
        String currentBranchId = todoList.getCurrentBranch().getId();
        Branch result = todoList.getAllBranches().remove(key);
        if (result == null) {
            System.out.println(String.format("Given branch (%s) does not exists!", key));
            return;
        }
        System.out.println(String.format("Branch: %s (%s) removed successfully!", result.getName(), result.getId()));
        if (key.equals(currentBranchId)) {
            // Set another branch as the current branch
            Iterator<Map.Entry<String, Branch>> iterator = todoList.getAllBranches().entrySet().iterator();
            if (iterator.hasNext()) {
                // There is still another branch. Set it as the current branch.
                Map.Entry<String, Branch> entry = iterator.next();
                Branch newCurrentBranch = todoList.getAllBranches().get(entry.getKey());
                todoList.setCurrentBranch(Util.get8BitIdFromString(newCurrentBranch.getName()));
            } else {
                // There is no other branch. Set the currentBranchId to null.
                todoList.setCurrentBranch(null);
            }
        }
        Util.saveToListFile(todoList);
    }

    private void printAllBranch(TodoList todoList) {
        HashMap<String, Branch> branches = todoList.getAllBranches();
        if (branches.size() == 0) {
            System.out.println("There is no branch yet.");
            return;
        }

        String currentBranchId = todoList.getCurrentBranch().getId();
        // Print current branch at the first line
        System.out.println(String.format("* %s %s", branches.get(currentBranchId).getId(),
                branches.get(currentBranchId).getName()));
        // Print the rest of branches
        for (String key : branches.keySet()) {
            if (key.equals(currentBranchId)) {
                continue;
            }
            System.out.println(String.format("  %s %s", branches.get(key).getId(), branches.get(key).getName()));
        }
    }

}
