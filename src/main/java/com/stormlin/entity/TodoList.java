package com.stormlin.entity;

import com.stormlin.util.Const;
import com.stormlin.util.Util;

import java.io.Serializable;
import java.util.HashMap;

public class TodoList implements Serializable {

    private static final long serialVersionUID = -2287413049283852210L;

    private String currentBranchId;
    private HashMap<String, Branch> branches;

    public TodoList() {
        branches = new HashMap<>();
        String defaultBranchId = Util.get8BitIdFromString(Const.DEFAULT_BRANCH_NAME);
        branches.put(defaultBranchId, new Branch(Const.DEFAULT_BRANCH_NAME));
        currentBranchId = defaultBranchId;
    }

    public Branch getCurrentBranch() {
        return branches.get(currentBranchId);
    }

    public void setCurrentBranch(String branchId) {
        if (branchId == null) {
            // Set it to null if all branches are deleted
            currentBranchId = null;
            return;
        }
        if (branches.containsKey(branchId)) {
            // Set it to an existing branch
            currentBranchId = branchId;
            return;
        }
        System.out.println(String.format("Branch: %s do not exists.", branchId));
    }

    public HashMap<String, Branch> getAllBranches() {
        return branches;
    }

}
