package main.com.stormlin;

import java.io.Serializable;
import java.util.ArrayList;

public class TodoList implements Serializable {

    private static final long serialVersionUID = -2287413049283852210L;

    private String currentBranch;
    private ArrayList<Branch> branches;

    public String getCurrentBranch() {
        return currentBranch;
    }

    public void setCurrentBranch(String currentBranch) {
        this.currentBranch = currentBranch;
    }

    TodoList() {
        branches = new ArrayList<>();
        this.branches.add(new Branch("default"));
        this.currentBranch = Util.get8BitIdFromString("default");
    }


}
