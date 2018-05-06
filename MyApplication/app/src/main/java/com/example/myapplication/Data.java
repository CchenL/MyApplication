package com.example.myapplication;

/**
 * Created by Administrator on 2018-4-27.
 */
public class Data {
    private String content;

    public Data(String s) {}

    public Data(int imgId, String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}