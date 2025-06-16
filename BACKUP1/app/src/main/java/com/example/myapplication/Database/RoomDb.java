package com.example.myapplication.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.Database.Dao.DishesDao;
import com.example.myapplication.Database.Dao.FitnessPlansDao;
import com.example.myapplication.Database.Dao.UserDao;
import com.example.myapplication.Database.Entity.Dishes;
import com.example.myapplication.Database.Entity.FitnessPlans;
import com.example.myapplication.Database.Entity.User;

import java.util.concurrent.Executors;

@Database(entities = {User.class, Dishes.class, FitnessPlans.class}, version = 1)
public abstract class RoomDb extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract DishesDao dishesDao();
    public abstract FitnessPlansDao fitnessPlansDao();

    public static RoomDb INSTANCE;

    public synchronized static RoomDb getINSTANCE(Context context)
    {
        if(INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context,RoomDb.class,"Meal.db").addCallback(new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    DishesDao dishesDao = INSTANCE.dishesDao();
                    FitnessPlansDao fitnessPlansDao = INSTANCE.fitnessPlansDao();
                    UserDao userDao = INSTANCE.userDao();


                    Executors.newSingleThreadExecutor().submit(()->{




                        dishesDao.insertDishesAll(new Dishes("Appetizer","Squash Okay",71));
                        dishesDao.insertDishesAll(new Dishes("Appetizer","Dynamite Lumpia",100));
                        dishesDao.insertDishesAll(new Dishes("Appetizer","Ensaladang Pipino",60));

                        dishesDao.insertDishesAll(new Dishes("Beef","Tapa",450));
                        dishesDao.insertDishesAll(new Dishes("Beef","Beef Giniling",597));
                        dishesDao.insertDishesAll(new Dishes("Beef","Beef Caldereta",450));

                        dishesDao.insertDishesAll(new Dishes("Chicken","Adobo Chicken",300));
                        dishesDao.insertDishesAll(new Dishes("Chicken","Chicken Inasal",350));
                        dishesDao.insertDishesAll(new Dishes("Chicken","Crispy Fried Chicken",310));

                        dishesDao.insertDishesAll(new Dishes("Pork","Pork Kare Kare",450));
                        dishesDao.insertDishesAll(new Dishes("Pork","Lechon",600));
                        dishesDao.insertDishesAll(new Dishes("Pork","Porn Sinigang",310));

                        dishesDao.insertDishesAll(new Dishes("Seafood","Rellenong Hipon",107));
                        dishesDao.insertDishesAll(new Dishes("Seafood","Crispy Tahong",360));
                        dishesDao.insertDishesAll(new Dishes("Seafood","Ginataang Alimango",450));


                        dishesDao.insertDishesAll(new Dishes("Dessert","Halo-Halo",350));
                        dishesDao.insertDishesAll(new Dishes("Dessert","Buko Pandan",320));
                        dishesDao.insertDishesAll(new Dishes("Dessert","Ube Halaya",280));
                    });
                }
            }).build();
        }

        return INSTANCE;
    }
}