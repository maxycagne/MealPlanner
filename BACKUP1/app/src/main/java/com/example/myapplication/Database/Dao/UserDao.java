package com.example.myapplication.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Database.Entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAllUser();

    @Query("SELECT * FROM user WHERE id LIKE :id LIMIT 1")
    User findUserById(int id);

    @Insert
    void insertUserAll(User users);

    @Delete
    void deleteUser(User user);
}
