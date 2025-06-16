package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Database.Entity.Dishes;
import com.example.myapplication.databinding.CardSelectMealsBinding;

import java.util.List;

public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.ViewHolder> {

    private Context context;

    private List<Dishes> dishesList;

    private DishesClick dishesClick;

    public DishesAdapter(Context context, List<Dishes> dishesList, DishesClick dishesClick) {
        this.context = context;
        this.dishesList = dishesList;
        this.dishesClick = dishesClick;
    }

    public interface DishesClick
    {
        void OnClick(Dishes dishes);
    }

    public void refreshList(Context context)
    {
        this.dishesList = dishesList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DishesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(CardSelectMealsBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DishesAdapter.ViewHolder holder, int position) {
        Dishes dishes = dishesList.get(position);

        holder.root.txtDishName.setText(dishes.getDish());

        holder.itemView.setOnClickListener(view -> {
            dishesClick.OnClick(dishes);
        });
    }

    @Override
    public int getItemCount() {
        return dishesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardSelectMealsBinding root;
        public ViewHolder(@NonNull CardSelectMealsBinding root) {
            super(root.getRoot());
            this.root = root;
        }
    }
}
