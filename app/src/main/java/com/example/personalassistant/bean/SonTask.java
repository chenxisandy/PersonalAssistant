package com.example.personalassistant.bean;

public class SonTask {    //泛型是为了以后的转型方便，所以不需要，copy过去之后数据也是重建一份

    private String title;

    //private Fatherable father;

    private Fatherable father;   //t可取Son task 或者 long task

    public SonTask(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Fatherable getFather() {
        return father;
    }

    public void setFather(Fatherable father) {
        this.father = father;
    }
}
