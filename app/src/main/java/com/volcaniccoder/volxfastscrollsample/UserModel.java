package com.volcaniccoder.volxfastscrollsample;

import com.volcaniccoder.volxfastscroll.ValueArea;

public class UserModel {

    private int counter;
    @ValueArea
    private String name;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
