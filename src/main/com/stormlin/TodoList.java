package main.com.stormlin;

import java.io.Serializable;
import java.util.HashMap;

public class TodoList implements Serializable {

    private static final long serialVersionUID = -2287413049283852210L;

    private String currentBranchId;
    private HashMap<String, Branch> branches;

    Branch getCurrentBranch() {
        return branches.get(currentBranchId);
    }

    public void setCurrentBranch(String branchId) {
        if (branches.containsKey(branchId)) {
            currentBranchId = branchId;
        }
        System.out.println(String.format("Branch: %s do not exists.", branchId));
    }

    TodoList() {
        branches = new HashMap<>();
        String defaultBranchId = Util.get8BitIdFromString(Const.DEFAULT_BRANCH_NAME);
        branches.put(defaultBranchId, new Branch(Const.DEFAULT_BRANCH_NAME));
        currentBranchId = defaultBranchId;
    }


}
