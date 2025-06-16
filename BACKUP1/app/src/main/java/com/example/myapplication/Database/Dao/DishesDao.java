package com.example.myapplication.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.Database.Entity.Dishes;
import com.example.myapplication.Database.Entity.User;

import java.util.List;

@Dao
public interface DishesDao {

    @Query("SELECT * FROM dishes")
    List<Dishes> getAllDishes();

    @Query("SELECT dish FROM dishes")
    List<Dishes> getDishes();




    @Query("SELECT * FROM dishes WHERE id LIKE :id LIMIT 1")
    Dishes findDishesById(int id);

    @Insert
    void insertDishesAll(Dishes dishes);

    @Delete
    void deleteUser(Dishes dishes);
}
