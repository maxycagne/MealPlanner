package com.example.myapplication.Fragment;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.myapplication.Activity.ActivityAdd;
import com.example.myapplication.Activity.ActivityDashboard;
import com.example.myapplication.Activity.ViewPlanActivity;
import com.example.myapplication.Adapter.FitnessAdapter;
import com.example.myapplication.Database.Dao.FitnessPlansDao;
import com.example.myapplication.Database.Dbhelper;
import com.example.myapplication.Database.Entity.FitnessPlans;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.databinding.FragmentProfileBinding;

import java.util.List;
import java.util.concurrent.Executors;


public class HomeFragment extends Fragment {


    private FragmentHomeBinding root;

    private Dbhelper dbhelper;

    private Handler handler;

    private void setDb()
    {
        dbhelper = new Dbhelper(getContext());
        handler = new Handler(Looper.getMainLooper());
    }


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retrievePlans();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = FragmentHomeBinding.inflate(inflater,container,false);
        setDb();



        String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item, days);
        root.txtFilter.setAdapter(arrayAdapter);

        retrievePlans();

        root.btnAdd.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), ActivityAdd.class));
        });

        return root.getRoot();
    }

    public void retrievePlans()
    {
        Executors.newSingleThreadExecutor().submit(()->{
            FitnessPlansDao fitnessPlansDao = ((ActivityDashboard)getActivity()).getDbhelper().getFitnessPlansDao();
            List<FitnessPlans> fitnessPlansList = fitnessPlansDao.getAllFitnessPlans();

            ((ActivityDashboard)getActivity()).getHandler().post(()->{
                try{
                    if(fitnessPlansList.isEmpty())
                    {
                        root.recyclerHome.setVisibility(View.GONE);
                        root.txtMessageGroup.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        root.recyclerHome.setVisibility(View.VISIBLE);
                        root.txtMessageGroup.setVisibility(View.GONE);
                        FitnessAdapter adapter = new FitnessAdapter(getContext(), fitnessPlansList, new FitnessAdapter.FitnessPlansClick() {
                            @Override
                            public void Onclick(FitnessPlans fitnessPlans) {
                                startActivity(new Intent(getContext(), ViewPlanActivity.class));
                            }
                        });
                        root.recyclerHome.setAdapter(adapter);
                    }
                }catch (Exception exception)
                {
                    Toast.makeText(getContext(),exception.getMessage(),Toast.LENGTH_SHORT).show();
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