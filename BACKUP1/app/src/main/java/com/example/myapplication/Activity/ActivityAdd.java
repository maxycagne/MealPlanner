package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class ActivityAdd extends AppCompatActivity {

    private ActivityAddBinding root;

    private Dbhelper dbhelper;

    private Handler handler;

    private List<String> mealList;
    private ArrayAdapter<String> mealAdapter;


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

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, days);
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
            root.txtCategory.setText("Snacks");
        });

        root.progressBarkcal.setProgress(50);

        root.btnBack.setOnClickListener(view -> {
            startActivity(new Intent(this,ActivityDashboard.class));
        });

        root.buttonAdd.setOnClickListener(v->{
            addPlans();
        });

        mealList = new ArrayList<>();
        mealAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mealList);
        root.listView.setAdapter(mealAdapter);

    }

    private String getAllListViewText() {
        StringBuilder dishes = new StringBuilder();
        for (int i = 0; i < root.listView.getAdapter().getCount(); i++) {
            dishes.append(root.listView.getAdapter().getItem(i).toString()).append(", ");
        }

        return dishes.length() > 0 ? dishes.substring(0, dishes.length() - 2) : "";
    }


    public void addPlans()
    {
        String planName = root.txtName.getText().toString();
        String day = root.txtDay.getText().toString();
        String totalCal = root.txtKcalProgress.getText().toString();
        String category = root.txtCategory.getText().toString();
        String dishList = getAllListViewText();



        Executors.newSingleThreadExecutor().submit(()->{
            FitnessPlans fitnessPlans = new FitnessPlans(planName,totalCal,day,category,dishList);
            dbhelper.getFitnessPlansDao().insertFitnessPlansAll(fitnessPlans);


            handler.post(()->{
                Toast.makeText(this,"Fitness plan added.",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,ActivityDashboard.class));
            });
        });
    }


    public void retrievePlans()
    {
        Executors.newSingleThreadExecutor().submit(()->{
            List<Dishes> dishesList = dbhelper.getDishesDao().getAllDishes();

            handler.post(()->{
                try{
                        DishesAdapter adapter = new DishesAdapter(this, dishesList, new DishesAdapter.DishesClick() {
                            @Override
                            public void OnClick(Dishes dishes) {
                                String dishEntry = dishes.getDish() + " - " + dishes.getFoodCategory() + " (" + dishes.getCalories() + " kcal)";

                                // Ensure the list updates with every click
                                mealList.add(dishEntry); // Always add the new dish
                                mealAdapter.notifyDataSetChanged();




                            }
                        });
                        root.recyclerFood.setAdapter(adapter);

                }catch (Exception exception)
                {
                    Toast.makeText(this,exception.getMessage(),Toast.LENGTH_SHORT).show();
                }

            });
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        retrievePlans();
    }








}