package com.example.personalassistant.bean;

import com.example.personalassistant.model.Repo;

import java.util.ArrayList;
import java.util.List;

//实现任务清单的创建、删除、修改和按照不同规则排序（至少
//2 种规则）：名字，类型

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

    public Task getTask(int index) {
        return taskList.get(index);
    }
}
