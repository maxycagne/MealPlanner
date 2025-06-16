package com.example.myapplication.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.Database.Entity.Dishes;
import com.example.myapplication.Database.Entity.FitnessPlans;

import java.util.List;

@Dao
public interface FitnessPlansDao {

    @Query("SELECT * FROM fitnessPlans")
    List<FitnessPlans> getAllFitnessPlans();

    @Query("SELECT * FROM fitnessplans WHERE id LIKE :id LIMIT 1")
    FitnessPlans findFitnessPlansById(int id);

    @Insert
    void insertFitnessPlansAll(FitnessPlans fitnessPlans);

    @Delete
    void deleteFitnessPlans(FitnessPlans fitnessPlans);
}
