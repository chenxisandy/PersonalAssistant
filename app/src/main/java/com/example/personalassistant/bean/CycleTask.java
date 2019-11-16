package com.example.personalassistant.bean;

import com.example.personalassistant.Constant;

public class CycleTask extends Task {

    private int repeatTimes;

    private int interval;

    public CycleTask(String title, String content, String time, boolean isFinish) {
        type = Constant.CYCLE_TASK;
        this.title = title;
        this.content = content;
        this.time = time;
        this.isFinish = isFinish;
    }

    public CycleTask() {
        type = Constant.CYCLE_TASK;
    }

    public int getRepeatTimes() {
        return repeatTimes;
    }

    public void setRepeatTimes(int repeatTimes) {
        this.repeatTimes = repeatTimes;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
