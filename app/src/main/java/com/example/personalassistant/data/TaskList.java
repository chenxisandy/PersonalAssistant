package com.example.personalassistant.data;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private String name;

    private String type;

    private List<Task> taskList;

    public TaskList(String name, String type, List<Task> taskList) {
        this.name = name;
        this.type = type;
        this.taskList = taskList;
        if (taskList == null) {
            taskList = new ArrayList<>();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(Task task) {
        taskList.remove(task);
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
