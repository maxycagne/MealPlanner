package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.Activity.ActivityWelcomePage;
import com.example.myapplication.Database.Dbhelper;
import com.example.myapplication.Database.Entity.FitnessPlans;
import com.example.myapplication.Database.Entity.User;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityWelcomePageBinding;
import com.example.myapplication.databinding.FragmentProfileBinding;

import java.util.List;
import java.util.concurrent.Executors;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding root;

    private Dbhelper dbhelper;

    private Handler handler;

    private void setDb()
    {
        dbhelper = new Dbhelper(getContext());
        handler = new Handler(Looper.getMainLooper());
    }

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = FragmentProfileBinding.inflate(inflater,container,false);
        setDb();

        root.btnLogout.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), ActivityWelcomePage.class));
        });

        root.buttonSetName.setOnClickListener(view -> {
        });

        retrieve();
        return root.getRoot();
    }

    public void retrieve()
    {
        Executors.newSingleThreadExecutor().submit(() ->{
            User user = (User) dbhelper.getUserDao().getAllUser();
            Toast.makeText(getContext(),"user not found",Toast.LENGTH_SHORT).show();
            root.txtName.setText(user.getAppName());
        });
    }





}