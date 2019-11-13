package com.example.personalassistant.bean;

import com.example.personalassistant.Constant;
import com.example.personalassistant.model.Repo;

import java.util.ArrayList;
import java.util.List;

//暂时舍弃，因为与longTask重复

public class LongTask extends Task implements Fatherable{

    public LongTask(String title, String content, String time, boolean isFinish) {
        type = Constant.CYCLE_TASK;
        this.title = title;
        this.content = content;
        this.time = time;
        this.isFinish = isFinish;
    }

    public LongTask() {
        type = Constant.CYCLE_TASK;
    }

//    public void addTask(LongTask task) {
//        if (taskList == null) {
//            taskList = new ArrayList<>();
//        }
//        taskList.add(task);
//    }
//
//    public void deleteTask(LongTask task) {
//        taskList.remove(task);
//    }
//
//    public List<LongTask> getTaskList() {
//        return taskList;
//    }


    @Override
    public List<SonTask> getSonListFromRepo() {
        return Repo.getInstance().getSonListByFather(this);
    }
}
