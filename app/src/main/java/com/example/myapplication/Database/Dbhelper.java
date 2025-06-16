package com.example.myapplication.Database;

import android.content.Context;

import com.example.myapplication.Database.Dao.DishesDao;
import com.example.myapplication.Database.Dao.FitnessPlansDao;
import com.example.myapplication.Database.Dao.UserDao;

public class Dbhelper {

    private RoomDb roomDb;
    private UserDao userDao;
    private DishesDao dishesDao;
    private FitnessPlansDao fitnessPlansDao;

    public Dbhelper(Context context) {
        this.roomDb = RoomDb.getINSTANCE(context);
        this.userDao = roomDb.userDao();
        this.dishesDao = roomDb.dishesDao();
        this.fitnessPlansDao = roomDb.fitnessPlansDao();
    }

    public RoomDb getRoomDb() {
        return roomDb;
    }

    public FitnessPlansDao getFitnessPlansDao() {
        return fitnessPlansDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public DishesDao getDishesDao() {
        return dishesDao;
    }
}
