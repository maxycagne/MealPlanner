package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Adapter.DishesAdapter;
import com.example.myapplication.Adapter.FitnessAdapter;
import com.example.myapplication.Database.Dao.FitnessPlansDao;
import com.example.myapplication.Database.Dbhelper;
import com.example.myapplication.Database.Entity.Dishes;
import com.example.myapplication.Database.Entity.FitnessPlans;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityAddBinding;
import com.example.myapplication.databinding.ActivityDashboardBinding;

import java.util.List;
import java.util.concurrent.Executors;

public class ActivityAdd extends AppCompatActivity {

    private ActivityAddBinding root;

    private Dbhelper dbhelper;

    private Handler handler;

    private void setDb()
    {
        dbhelper = new Dbhelper(this);
        handler = new Handler(Looper.getMainLooper());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        root = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(root.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, days);
        root.txtDay.setAdapter(arrayAdapter);

        setDb();


        root.buttonSetKcal.setOnClickListener(v->{
            int setCal = Integer.parseInt(root.txtSetKcal.getText().toString());
            root.txtKcallFullProgress.setText(String.valueOf(setCal));
        });


        root.btnBreakfast.setOnClickListener(view ->
        {
            root.btnBreakfast.setBackgroundColor(getColor(R.color.ten));
            root.btnLunch.setBackgroundColor(getColor(R.color.black));
            root.btnDinner.setBackgroundColor(getColor(R.color.black));
            root.btnSnacks.setBackgroundColor(getColor(R.color.black));

            root.txtCategory.setText("Breakfast");
        });
        root.btnLunch.setOnClickListener(view -> {
            root.btnLunch.setBackgroundColor(getColor(R.color.ten));
            root.btnDinner.setBackgroundColor(getColor(R.color.black));
            root.btnSnacks.setBackgroundColor(getColor(R.color.black));
            root.btnBreakfast.setBackgroundColor(getColor(R.color.black));
            root.txtCategory.setText("Lunch");
        });
        root.btnDinner.setOnClickListener(view -> {
            root.btnDinner.setBackgroundColor(getColor(R.color.ten));
            root.btnSnacks.setBackgroundColor(getColor(R.color.black));
            root.btnLunch.setBackgroundColor(getColor(R.color.black));
            root.btnBreakfast.setBackgroundColor(getColor(R.color.black));
            root.txtCategory.setText("Dinner");
        });
        root.btnSnacks.setOnClickListener(view -> {
            root.btnSnacks.setBackgroundColor(getColor(R.color.ten));
            root.btnDinner.setBackgroundColor(getColor(R.color.black));
            root.btnLunch.setBackgroundColor(getColor(R.color.black));
            root.btnBreakfast.setBackgroundColor(getColor(R.color.black));
            root.txtCategory.setText("Breakfast");
        });

        root.progressBarkcal.setProgress(50);

        root.btnBack.setOnClickListener(view -> {
            startActivity(new Intent(this,ActivityDashboard.class));
        });

        root.buttonAdd.setOnClickListener(v->{
            addPlans();
        });



    }

    public void addPlans()
    {
        String planName = root.txtName.getText().toString();
        String day = root.txtDay.getText().toString();
        String totalCal = root.txtKcalProgress.getText().toString();
        String category = root.txtCategory.getText().toString();
        String dishies = root.txtDish.getText().toString();


        Executors.newSingleThreadExecutor().submit(()->{
            FitnessPlans fitnessPlans = new FitnessPlans(planName,totalCal,day,category,dishies);
            dbhelper.getFitnessPlansDao().insertFitnessPlansAll(fitnessPlans);


            handler.post(()->{
                Toast.makeText(this,"Fitness plan added.",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,ActivityDashboard.class));
            });
        });
    }










}