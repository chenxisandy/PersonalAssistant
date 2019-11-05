package com.example.personalassistant.data;

import com.example.personalassistant.Constant;

import java.util.ArrayList;
import java.util.List;

//暂时舍弃，因为与longTask重复

public class LongTask extends Task {

    private List<LongTask> taskList;

    public LongTask(String title, String content, String time, boolean isFinish) {
        type = Constant.CYCLE_TASK;
        this.title = title;
        this.content = content;
        this.time = time;
        this.isFinish = isFinish;
        taskList = new ArrayList<>();
    }

    public LongTask() {
        type = Constant.CYCLE_TASK;
    }

    public void addTask(LongTask task) {
        if (taskList == null) {
            taskList = new ArrayList<>();
        }
        taskList.add(task);
    }

    public void deleteTask(LongTask task) {
        taskList.remove(task);
    }

    public List<LongTask> getTaskList() {
        return taskList;
    }
}
