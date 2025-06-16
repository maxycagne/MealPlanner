package com.example.myapplication.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Database.Dbhelper;
import com.example.myapplication.Database.Entity.User;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityWelcomePageBinding;

import java.util.List;
import java.util.concurrent.Executors;

public class ActivityWelcomePage extends AppCompatActivity {

    private ActivityWelcomePageBinding root;

    private Dbhelper dbhelper;

    private Handler handler;

    private String username;

    private void setDb()
    {
        dbhelper = new Dbhelper(this);
        handler = new Handler(Looper.getMainLooper());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        root = ActivityWelcomePageBinding.inflate(getLayoutInflater());
        setContentView(root.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setDb();

        root.btnNext.setOnClickListener(v->{
            addUser();

            finish();
        });
    }

    public void addUser()
    {

        username = root.txtUsername.getText().toString();
        Executors.newSingleThreadExecutor().submit(()->{
            User user = new User(username,0);
            dbhelper.getUserDao().insertUserAll(user);
            startActivity(new Intent(this,ActivityDashboard.class));
        });
    }


}