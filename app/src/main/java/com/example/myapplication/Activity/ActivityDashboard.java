package com.example.myapplication.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.Database.Dbhelper;
import com.example.myapplication.Fragment.HomeFragment;
import com.example.myapplication.Fragment.PlansFragment;
import com.example.myapplication.Fragment.ProfileFragment;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityDashboardBinding;

public class ActivityDashboard extends AppCompatActivity {

    private ActivityDashboardBinding root;
    private Dbhelper dbhelper;

    private Handler handler;

    private void setDb()
    {
        dbhelper = new Dbhelper(this);
        handler = new Handler(Looper.getMainLooper());
    }



    public Dbhelper getDbhelper() {
        return dbhelper;
    }

    public Handler getHandler() {
        return handler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        root = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(root.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });



        setDb();

        ReplaceFragment(new HomeFragment());
        root.bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.menuHome)
            {
                ReplaceFragment(new HomeFragment());
            }
            else if(item.getItemId() == R.id.menuPlans)
            {
                ReplaceFragment(new PlansFragment());
            }
            else if(item.getItemId() == R.id.menuProfile)
            {
                ReplaceFragment(new ProfileFragment());
            }

            return true;
        });
    }

    private void ReplaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameDash,fragment);
        fragmentTransaction.commit();
    }
}