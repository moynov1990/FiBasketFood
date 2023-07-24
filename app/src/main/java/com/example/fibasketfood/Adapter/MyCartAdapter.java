package com.example.fibasketfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Interface.ItemClickListener;
import com.example.fibasketfood.Model.CartModel;
import com.example.fibasketfood.R;

import java.util.ArrayList;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyCartViewHolder> {

    private Context context;
    ArrayList<CartModel> cartModelList;
//    String quantity = "1";
//    String item = "шт";
//    OrderDBHelper dbHelper;


    public MyCartAdapter(Context context, ArrayList<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
    }

    @NonNull
    @Override
    public MyCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new MyCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartViewHolder holder, int position) {
        CartModel cartModel = cartModelList.get(position);

        String name = cartModel.getName();
        String quantity = cartModel.getQuantity();
        String item = cartModel.getItem();

        holder.itemName.setText(""+name);
        holder.tvCount.setText(""+quantity);
        holder.itemName.setText(""+item);
    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }


    public class MyCartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtCartFoodName, tvCount, itemName, imageMinus, imagePlus;

        ItemClickListener listener;



        public void setListener(ItemClickListener listener) {
            this.listener = listener;
        }

        public MyCartViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCartFoodName = itemView.findViewById(R.id.txtCartFoodName);
            tvCount = itemView.findViewById(R.id.tvCount);
            itemName = itemView.findViewById(R.id.itemName);
            imageMinus = itemView.findViewById(R.id.imageMinus);
            imagePlus = itemView.findViewById(R.id.imagePlus);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAbsoluteAdapterPosition());
        }
    }

}
