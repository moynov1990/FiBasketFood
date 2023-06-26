package com.example.fibasketfood.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.fibasketfood.Interface.ItemClickListener;
import com.example.fibasketfood.Model.Cart;
import com.example.fibasketfood.Model.Food;
import com.example.fibasketfood.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CartViewHolder extends RecyclerView.Adapter<CartViewHolder.cHolder> {

    private List<Food> foodList;

    public CartViewHolder(List<Food> foodList) {
        this.foodList = foodList;
    }

    public void updateData(List<Food> foodList) {
        this.foodList = foodList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public cHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return  new cHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cHolder holder, int position) {
        holder.txtFoodName.setText(foodList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    static class cHolder extends RecyclerView.ViewHolder {
        TextView txtFoodName;

        public cHolder(View view) {
            super(view);
            txtFoodName = view.findViewById(R.id.txtFoodName);
        }
    }
}
