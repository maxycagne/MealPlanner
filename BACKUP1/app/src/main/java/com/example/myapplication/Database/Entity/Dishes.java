package com.example.myapplication.Database.Entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Dishes implements Serializable {

    @PrimaryKey (autoGenerate = true)
    private int id;

    private String foodCategory;

    private String dish;

    private int calories;

    @Ignore
    public Dishes() {

    }


    @Ignore
    public Dishes(String foodCategory, String dish, int calories) {
        this.foodCategory = foodCategory;
        this.dish = dish;
        this.calories = calories;
    }

    public Dishes(int id, String foodCategory, String dish, int calories) {
        this.id = id;
        this.foodCategory = foodCategory;
        this.dish = dish;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
