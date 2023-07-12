package com.example.fibasketfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fibasketfood.Model.FoodModel;
import com.example.fibasketfood.R;

import java.util.List;

public class MyFoodAdapter extends RecyclerView.Adapter<MyFoodAdapter.MyFoodViewHolder> {

    private Context context;
    List<FoodModel> foodModelList;

    public MyFoodAdapter(Context context, List<FoodModel> foodModelList) {
        this.context = context;
        this.foodModelList = foodModelList;
    }

    @NonNull
    @Override
    public MyFoodAdapter.MyFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyFoodAdapter.MyFoodViewHolder(LayoutInflater.from(context).inflate(R.layout.food_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyFoodAdapter.MyFoodViewHolder holder, int position) {
        Glide.with(context).load(foodModelList.get(position).getImage()).into(holder.imgFoodView);
        holder.txtFoodName.setText(new StringBuilder().append(foodModelList.get(position).getName()));
    }

    @Override
    public int getItemCount() {
        return foodModelList.size();
    }

    public class MyFoodViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFoodView;
        TextView txtFoodName;

        public MyFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFoodView = itemView.findViewById(R.id.imgFoodView);
            txtFoodName = itemView.findViewById(R.id.txtFoodName);
        }
    }

}
