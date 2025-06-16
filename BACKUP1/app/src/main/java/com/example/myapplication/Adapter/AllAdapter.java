package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Database.Entity.FitnessPlans;
import com.example.myapplication.databinding.CardAllPlansBinding;
import com.example.myapplication.databinding.CardSelectMealsBinding;

import java.util.List;

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.ViewHolder> {
    private Context context;

    private List<FitnessPlans> fitnessPlansList;

    private AllAdapter.FitnessPlansClick fitnessPlansClick;

    public AllAdapter(Context context, List<FitnessPlans> fitnessPlansList, AllAdapter.FitnessPlansClick fitnessPlansClick) {
        this.context = context;
        this.fitnessPlansList = fitnessPlansList;
        this.fitnessPlansClick = fitnessPlansClick;
    }

    public interface FitnessPlansClick{
        void Onclick(FitnessPlans fitnessPlans);
    }

    public void refreshList(Context context)
    {
        this.fitnessPlansList = fitnessPlansList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(CardAllPlansBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllAdapter.ViewHolder holder, int position) {
        FitnessPlans fitnessPlans = fitnessPlansList.get(position);

        holder.root.txtMealCategory.setText(fitnessPlans.getCategory());
        holder.root.txtMealName.setText(fitnessPlans.getPlanName());
        holder.root.txtTotalKcall.setText(fitnessPlans.getTotalcalories());
        holder.root.txtDishName.setText(fitnessPlans.getDishes());

        holder.itemView.setOnClickListener(view->{
            fitnessPlansClick.Onclick(fitnessPlans);
        });
    }

    @Override
    public int getItemCount() {
        return fitnessPlansList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardAllPlansBinding root;
        public ViewHolder(@NonNull CardAllPlansBinding root) {
            super(root.getRoot());
            this.root =root;
        }
    }
}
