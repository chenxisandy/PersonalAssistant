package com.example.personalassistant.bean;

import com.example.personalassistant.Constant;

public class ShortTask extends Task {

    public ShortTask(String title, String content, String time, boolean isFinish) {
        super.type = Constant.SHORT_TASK;
        super.title = title;
        super.content = content;
        super.time = time;
        super.isFinish = isFinish;
    }

}
