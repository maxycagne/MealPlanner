package com.example.myapplication.Database.Entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class FitnessPlans implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private int id;


    private String planName;
    private String totalcalories;

    private String dishes;

    private String category;

    private String day;

    @Ignore
    public FitnessPlans() {

    }

    @Ignore
    public FitnessPlans(String planName, String totalcalories, String dishes, String category, String day) {
        this.planName = planName;
        this.totalcalories = totalcalories;
        this.dishes = dishes;
        this.category = category;
        this.day = day;
    }

    public FitnessPlans(int id, String planName, String totalcalories, String dishes, String category, String day) {
        this.id = id;
        this.planName = planName;
        this.totalcalories = totalcalories;
        this.dishes = dishes;
        this.category = category;
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getTotalcalories() {
        return totalcalories;
    }

    public void setTotalcalories(String totalcalories) {
        this.totalcalories = totalcalories;
    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
