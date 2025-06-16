package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityEditBinding;

public class ActivityEdit extends AppCompatActivity {

    private ActivityEditBinding root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        root = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(root.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        root.btnBack.setOnClickListener(v->{
            startActivity(new Intent(this,ActivityDashboard.class));
        });

        root.btnBack.setOnClickListener(v->{
            startActivity(new Intent(this,ActivityDashboard.class));
        });

    }
}