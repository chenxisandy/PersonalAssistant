package com.example.personalassistant.bean;


//实现三种类型任务的创建、删除、修改和按照不同规则排序（至
//少 3 种规则）
//排序：时间，标题，内容
// ，实现任务的查找以及在任务清单间的转移和复制；

public abstract class Task {

    protected String title;

    protected String content;

    protected String time;

    protected boolean isFinish;

    protected String type;

    private TaskList taskList;

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

    public TaskList getTaskList() {
        return taskList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
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
