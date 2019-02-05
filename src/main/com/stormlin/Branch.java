package main.com.stormlin;

import java.io.Serializable;
import java.util.HashMap;

public class Branch implements Serializable {

    private static final long serialVersionUID = -8578525119129216133L;

    private String id;
    private String name;
    private HashMap<String, Task> tasks;

    public String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Branch(String name) {
        id = Util.get8BitIdFromString(name);
        this.name = name;
        tasks = new HashMap<>();
    }

    HashMap<String, Task> getTaskList() {
        return tasks;
    }

    void addTask(String title, String priority) {
        String key = Util.get8BitIdFromString(title);
        if (!tasks.containsKey(key)) {
            tasks.put(key, new Task(title, priority));
            System.out.println(String.format("Task: %s added successfully!", key));
        } else {
            System.out.println(String.format("Task: %s title exists!", title));
        }
    }

    public void removeTask(String id) {
        if (!tasks.containsKey(id)) {
            System.out.println(String.format("Task: %s does not exists!", id));
            return;
        }
        tasks.remove(id);
        System.out.println(String.format("Task: %s removed successfully!", id));
    }
}
