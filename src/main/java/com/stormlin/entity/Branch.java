package com.stormlin.entity;

import com.stormlin.util.Util;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Branch(String name) {
        id = Util.get8BitIdFromString(name);
        this.name = name;
        tasks = new HashMap<>();
    }

    public HashMap<String, Task> getTaskList() {
        return tasks;
    }

    public void addTask(String title, String priority) {
        String key = Util.get8BitIdFromString(title);
        if (!tasks.containsKey(key)) {
            tasks.put(key, new Task(title, priority));
            System.out.println(String.format("Task: %s (%s) added successfully!", key, title));
        } else {
            System.out.println(String.format("Task: %s (%s) exists!", key, title));
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
