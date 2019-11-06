package com.example.personalassistant.bean;

import java.util.List;

public interface Fatherable {   //可成为父亲的：一个接口，
    List<SonTask> getSonListFromRepo();
}
