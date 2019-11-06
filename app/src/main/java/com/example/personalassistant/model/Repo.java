package com.example.personalassistant.model;

import com.example.personalassistant.bean.SonTask;
import com.example.personalassistant.bean.Task;

import java.util.ArrayList;
import java.util.List;

public class Repo {

    //singleton
    private Repo(){
        taskList = new ArrayList<>();
        sonList = new ArrayList<>();
    }

    private static class RepoHolder {
        private static final Repo INSTANCE = new Repo();
    }

    public static Repo getInstance() {
        return RepoHolder.INSTANCE;
    }

    private List<Task> taskList;

    private List<SonTask> sonList;

    public List<Task> getTaskListByIndexList(List<Integer> taskIndexList) {
        List<Task> tasks = new ArrayList<>();
        for (Integer i :
                taskIndexList) {
            tasks.add(taskList.get(i));
        }
        return tasks;
    }

    public List<SonTask> getSonListByIndexList(List<Integer> sonIndexList) {
        List<SonTask> sonTasks = new ArrayList<>();
        for (Integer i :
                sonIndexList) {
            sonTasks.add(sonList.get(i));
        }
        return sonTasks;
    }

    public int getIndexFromWholeTask(Task task) {
        return taskList.indexOf(task);
    }

    public int getIndexFromWholeSon(SonTask task) {
        return sonList.indexOf(task);
    }

}
