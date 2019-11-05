package com.example.personalassistant.data;

import com.example.personalassistant.data.ShortTask;

public abstract class Task {

    protected String title;

    protected String content;

    protected String time;

    protected boolean isFinish;

    protected String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

//    public static Task createTask(String title, String content, String time, boolean isFinish, String type) {
//        switch (type) {
//            case Constant.SHORT_TASK:
//                return new ShortTask(title, content, time, isFinish);
//            case Constant.CYCLE_TASK:
//                return new
//        }
//    }

}
