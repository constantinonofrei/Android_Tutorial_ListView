package com.example.conofrei.listview.dto;

/**
 * Created by conofrei on 11/1/2016.
 */
public class SqlItem {

    private int id;
    private String name;
    private int score;

    public SqlItem(String name, int score, int id) {
        this.name = name;
        this.score = score;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
