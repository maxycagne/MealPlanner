package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Database.Entity.FitnessPlans;
import com.example.myapplication.databinding.CardMealsHomeBinding;
import com.example.myapplication.databinding.CardSelectMealsBinding;

import java.util.List;

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.ViewHolder> {
    private Context context;

    private List<FitnessPlans> fitnessPlansList;


    private FitnessPlansClick fitnessPlansClick;

    public PlansAdapter(Context context, List<FitnessPlans> fitnessPlansList, FitnessPlansClick fitnessPlansClick) {
        this.context = context;
        this.fitnessPlansList = fitnessPlansList;
        this.fitnessPlansClick = fitnessPlansClick;
    }

    public interface FitnessPlansClick
    {
        void OnClick(FitnessPlans fitnessPlans);
    }

    public void refreshList(Context context)
    {
        this.fitnessPlansList = fitnessPlansList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlansAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(CardMealsHomeBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlansAdapter.ViewHolder holder, int position) {
            FitnessPlans fitnessPlans = fitnessPlansList.get(position);
            holder.root.txtMealName.setText(fitnessPlans.getDishes());
            holder.root.txtMealCategory.setText(fitnessPlans.getCategory());
            holder.root.txtTotalKcall.setText(fitnessPlans.getTotalcalories());

            holder.itemView.setOnClickListener(view -> {
                fitnessPlansClick.OnClick(fitnessPlans);
            });
    }

    @Override
    public int getItemCount() {
        return fitnessPlansList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardMealsHomeBinding root;
        public ViewHolder(@NonNull CardMealsHomeBinding root) {
            super(root.getRoot());
            this.root = root;
        }
    }
}
