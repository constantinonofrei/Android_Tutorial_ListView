package com.example.conofrei.listview;

/**
 * Created by conofrei on 11/1/2016.
 */
public class RowBean {
    private String name;
    private int imgRsc;

    public RowBean(int imgRsc, String name) {
        this.imgRsc = imgRsc;
        this.name = name;
    }

    public int getImgRsc() {
        return imgRsc;
    }

    public String getName() {
        return name;
    }
}
