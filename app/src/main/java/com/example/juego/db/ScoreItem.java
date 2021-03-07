package com.example.juego.db;

public class ScoreItem {
    private int mId;
    private String mName;
    private Integer mScore;

    public ScoreItem() {
    }

    public int getId() {
        return this.mId;
    }

    public Integer getScore() {
        return this.mScore;
    }

    public String getName() {
        return this.mName;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public void setScore(Integer score) {
        this.mScore = score;
    }

    public void setName(String name) {
        this.mName = name;
    }

}

