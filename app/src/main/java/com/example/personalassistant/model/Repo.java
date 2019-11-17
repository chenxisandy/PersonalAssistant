package com.example.personalassistant.model;

import com.example.personalassistant.bean.Fatherable;
import com.example.personalassistant.bean.SonTask;
import com.example.personalassistant.bean.Task;
import com.example.personalassistant.bean.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Repo {

    //singleton
    private Repo(){
        sonList = new ArrayList<>();
        manifest = new ArrayList<>();
    }

    public List<TaskList> getManifest() {
        return manifest;
    }

    public void setManifest(List<TaskList> manifest) {
        this.manifest = manifest;
    }

    private static class RepoHolder {
        private static final Repo INSTANCE = new Repo();
    }

    public static Repo getInstance() {
        return RepoHolder.INSTANCE;
    }

    private List<SonTask> sonList;

    private List<TaskList> manifest;

    public List<SonTask> getSonList() {
        return sonList;
    }

    //    public List<Task> getTaskListByIndexList(List<Integer> taskIndexList) {
//        List<Task> tasks = new ArrayList<>();
//        for (Integer i :
//                taskIndexList) {
//            tasks.add(taskList.get(i));
//        }
//        return tasks;
//    }

    public List<SonTask> getSonListByFather(Fatherable fatherable) {
        List<SonTask> sonTasks = new ArrayList<>();
        for (SonTask sonTask :
                sonList) {
            if (sonTask.getFather().equals(fatherable)) {
                //此刻并未重写equals因此需要考虑一下
                sonTasks.add(sonTask);
            }
        }
        return sonTasks;
    }

    public void addSonTask(SonTask task) {
        sonList.add(task);
    }

    public void addManifest(TaskList mani) {
        manifest.add(mani);
    }

    public void deleteManifest(TaskList mani) {
        manifest.remove(mani);
    }

    public int getParentIndex(Task task) {
        for (TaskList ts :
                manifest) {
            for (Task t :
                    ts.getTaskList()) {
                if (task == t) {
                    return manifest.indexOf(ts);
                }
            }
        }
        return -1;
    }

    public TaskList getParent(Task task) {
        for (TaskList ts :
                manifest) {
            for (Task t :
                    ts.getTaskList()) {
                if (task == t) {
                    return ts;
                }
            }
        }
        return null;
    }

    public int getChildIndex(Task task) {
        for (TaskList ts :
                manifest) {
            for (Task t :
                    ts.getTaskList()) {
                if (task == t) {
                    return ts.getTaskList().indexOf(task);
                }
            }
        }
        return -1;
    }

//    public Map.Entry<Integer, Integer> getTaskPosition(Task task)

}
