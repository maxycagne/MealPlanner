package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Database.Dao.FitnessPlansDao;
import com.example.myapplication.Database.Entity.FitnessPlans;
import com.example.myapplication.R;
import com.example.myapplication.databinding.CardMealsHomeBinding;

import java.util.List;

public class FitnessAdapter extends RecyclerView.Adapter<FitnessAdapter.ViewHolder> {

    private Context context;

    private List<FitnessPlans> fitnessPlansList;

    private FitnessPlansClick fitnessPlansClick;

    public FitnessAdapter(Context context, List<FitnessPlans> fitnessPlansList, FitnessPlansClick fitnessPlansClick) {
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
    public FitnessAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(CardMealsHomeBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FitnessAdapter.ViewHolder holder, int position) {
            FitnessPlans fitnessPlans = fitnessPlansList.get(position);

            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

            holder.root.txtMealCategory.setText(fitnessPlans.getCategory());
            holder.root.txtMealName.setText(fitnessPlans.getPlanName());
            holder.root.txtTotalKcall.setText(fitnessPlans.getTotalcalories());

            holder.itemView.startAnimation(animation);

            holder.itemView.setOnClickListener(view->{
                fitnessPlansClick.Onclick(fitnessPlans);
            });
    }

    @Override
    public int getItemCount() {
        return fitnessPlansList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardMealsHomeBinding root;
        public ViewHolder(@NonNull CardMealsHomeBinding root) {
            super(root.getRoot());
            this.root = root;
        }
    }
}
