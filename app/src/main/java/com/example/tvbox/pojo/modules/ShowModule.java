package com.example.tvbox.pojo.modules;

public class ShowModule {
    private String name;
    private String title;
    private String time;

    public ShowModule(String name, String title, String time) {
        this.name = name;
        this.title = title;
        this.time = time;
    }


    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }
}
