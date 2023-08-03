package com.example.fibasketfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Database.OrderDBHelper;
import com.example.fibasketfood.Interface.ItemClickListener;
import com.example.fibasketfood.Model.CartModel;
import com.example.fibasketfood.R;

import java.util.ArrayList;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyCartViewHolder> {

    ArrayList<CartModel> recordsList;
//    String quantity = "1";
//    String item = "шт";
    OrderDBHelper dbHelper;
//    private Context context;


    public MyCartAdapter(ArrayList<CartModel> recordsList) {
        this.recordsList = recordsList;
//        this.context = context;
    }

    @NonNull
    @Override
    public MyCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new MyCartViewHolder(view);
//        dbHelper = new OrderDBHelper(context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartViewHolder holder, int position) {

        holder.txtCartFoodName.setText(recordsList.get(position).getName());
        holder.tvCount.setText(recordsList.get(position).getQuantity());
        holder.itemName.setText(recordsList.get(position).getItem());
    }

    @Override
    public int getItemCount() {
        return recordsList.size();
    }


    public class MyCartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtCartFoodName, tvCount, itemName;
        ImageView imageMinus, imagePlus, imgDelFromBasket;

        ItemClickListener listener;

        public void setListener(ItemClickListener listener) {
            this.listener = listener;
        }

        public MyCartViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCartFoodName = itemView.findViewById(R.id.txtCartFoodName);
            tvCount = itemView.findViewById(R.id.tvCountC);
            itemName = itemView.findViewById(R.id.itemName);
            imageMinus = itemView.findViewById(R.id.imageMinusC);
            imagePlus = itemView.findViewById(R.id.imagePlusC);
            imgDelFromBasket = itemView.findViewById(R.id.imgDelFromBasket);

            imgDelFromBasket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }


        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAbsoluteAdapterPosition());
        }
    }

}
