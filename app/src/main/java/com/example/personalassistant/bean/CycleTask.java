package com.example.personalassistant.bean;

import com.example.personalassistant.Constant;

public class CycleTask extends Task {

    private int frequency;

    private int interval;

    public CycleTask(String title, String content, String time, boolean isFinish) {
        type = Constant.CYCLE_TASK;
        this.title = title;
        this.content = content;
        this.time = time;
        this.isFinish = isFinish;
    }

    public CycleTask() {
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
