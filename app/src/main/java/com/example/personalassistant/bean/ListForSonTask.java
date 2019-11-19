package com.example.personalassistant.bean;

import java.util.List;

public class ListForSonTask {   //打算采用组合，son task很特殊，他不能在清单列表中查找，也不属于3大基本task

    private List<Integer> sonTaskIndexList;

    public ListForSonTask(List<Integer> sonTaskIndexList) {
        this.sonTaskIndexList = sonTaskIndexList;
    }

    public List<Integer> getSonTaskIndexList() {
        return sonTaskIndexList;
    }

    public void setSonTaskIndexList(List<Integer> sonTaskIndexList) {
        this.sonTaskIndexList = sonTaskIndexList;
    }
}
