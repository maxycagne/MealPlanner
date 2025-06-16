package com.example.myapplication.Database.Entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String appName;

    private int targetCal;

    @Ignore
    public User() {

    }


    @Ignore
    public User(String appName, int targetCal) {
        this.appName = appName;
        this.targetCal = targetCal;
    }

    public User(int id, String appName, int targetCal) {
        this.id = id;
        this.appName = appName;
        this.targetCal = targetCal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getTargetCal() {
        return targetCal;
    }

    public void setTargetCal(int targetCal) {
        this.targetCal = targetCal;
    }
}
