package com.example.personalassistant.data;

import java.util.ArrayList;
import java.util.List;

public class SonTask {

    private String title;

    private List<SonTask> sonTaskList = new ArrayList<>();

    public SonTask(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
