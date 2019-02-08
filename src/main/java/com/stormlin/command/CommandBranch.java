package com.stormlin.command;

import com.stormlin.entity.Branch;
import com.stormlin.entity.TodoList;
import com.stormlin.util.Const;
import com.stormlin.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CommandBranch implements ICommand {

    // All valid operations on branch
    public static final ArrayList<String> validBranchOperation = new ArrayList<>(
            Arrays.asList("add", "switch", "remove"));

    /**
     * This method prints the usage for command branch
     */
    public void usage() {
        System.out.println("Use this command to create, print, switch and delete branch.\n");
        System.out.println("usage: branch add branch-name");
        System.out.println("   or: branch switch branch-id");
        System.out.println("   or: branch remove branch-id");
        System.out.println("   or: branch [-h|--help]");
        System.out.println("   or: branch");
    }

    /**
     * This method executes the command concerning the branch
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

        String operation = args[0];
        if (!validBranchOperation.contains(operation)) {
            System.out.println(String.format("Invlid branch operation: %s", operation));
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

        switch (operation) {
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

    /**
     * Add a new branch in todo list
     * 
     * @param todoList Target todo list
     * @param args     Arguments from command line
     */
    private void addBranch(TodoList todoList, String[] args) {
        String key = Util.get8BitIdFromString(args[2]);
        if (todoList.getAllBranches().containsKey(key)) {
            System.out.println(
                    String.format("Branch %s (%s) already exists!", key, todoList.getAllBranches().get(key).getName()));
            return;
        }
        Branch newBranch = new Branch(args[2]);
        todoList.getAllBranches().put(key, newBranch);
        System.out.println(String.format("Branch %s (%s) added successfully!", newBranch.getId(), newBranch.getName()));
        if (todoList.getAllBranches().size() == 1) {
            todoList.setCurrentBranch(key);
            System.out.println(String.format("Branch %s (%s) is set to the current branch.", key, newBranch.getName()));
        }
        Util.saveToListFile(todoList);
    }

    /**
     * Switch to branch specified by branch-id
     * 
     * @param todoList Target todo list
     * @param args     Arguments from command line
     */
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

    /**
     * Remove the branch specified by branch-id
     * 
     * @param todoList Target todo list
     * @param args     Arguments from command line
     */
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

    /**
     * Print all branch in the terminal. Current branch will be marked by leading
     * star.
     * 
     * @param todoList Target todo list
     */
    private void printAllBranch(TodoList todoList) {
        HashMap<String, Branch> branches = todoList.getAllBranches();
        if (branches.size() == 0) {
            System.out.println("There is no branch yet.");
            return;
        }

        System.out.println("All branches are listed as below (current branch is marked by star):");
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
