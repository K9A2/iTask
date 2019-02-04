package main.com.stormlin;

import java.io.Serializable;
import java.util.ArrayList;

public class Branch implements Serializable {

    private static final long serialVersionUID = -8578525119129216133L;

    private String id;
    private String name;
    private ArrayList<Task> tasks;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Branch(String name) {
        this.id = Util.get8BitIdFromString(name);
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public void addTask(String title, String priority) {
        String id = Util.get8BitIdFromString(title);
        if (!this.tasks.contains(id)) {
            tasks.add(new Task(title, priority));
        } else {
            System.out.println("Task with identical title exists!");
        }
    }

    public void removeTask(String id) {
        this.tasks.remove(id);
    }

}
