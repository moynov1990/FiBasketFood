package com.example.fibasketfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fibasketfood.Database.OrderDBHelper;
import com.example.fibasketfood.Interface.ItemClickListener;
import com.example.fibasketfood.Model.FoodModel;
import com.example.fibasketfood.R;

import java.util.List;

public class MyFoodAdapter extends RecyclerView.Adapter<MyFoodAdapter.MyFoodViewHolder> {

    private Context context;
    List<FoodModel> foodModelList;
    String quantity = "1";
    String item = "шт";
    OrderDBHelper dbHelper;

    public MyFoodAdapter(Context context, List<FoodModel> foodModelList) {
        this.context = context;
        this.foodModelList = foodModelList;
        dbHelper = new OrderDBHelper(context);
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

        FoodModel model = foodModelList.get(position);
        String categoryID = model.getCategoryID();
        String foodID = model.getFoodID();
        String foodName = model.getName();

        holder.setListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int adapterPosition) {
                String categoryIDTV = ""+categoryID.toString().trim();
                String foodIDTV = ""+foodID.toString().trim();
                String foodNameTV = ""+foodName.toString().trim();
                String quantityTV = ""+quantity.toString().trim();
                String itemTV = ""+item.toString().trim();

                long result = dbHelper.insertRecord(
                        ""+categoryIDTV,
                        ""+foodIDTV,
                        ""+foodNameTV,
                        ""+quantityTV,
                        ""+itemTV
                );

                if(result > 0) {
                    Toast.makeText(context, "Додано в кошик" + result, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Fail" + result, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return foodModelList.size();
    }

//    public void swapCursor(Cursor newCursor) {
//        if (mCursor != null) {
//            mCursor.close();
//        }
//
//        mCursor = newCursor;
//
//        if (newCursor != null) {
//            notifyDataSetChanged();
//        }
//    }

//    private Cursor getAllItems() {
//        return mDatabase.query(
//                OrderContract.OrderEntry.TABLE_NAME,
//                null,
//                null,
//                null,
//                null,
//                null,
//                OrderContract.OrderEntry.COLUMN_TIMESTAMP + " DESC"
//        );
//    }

    public class MyFoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgFoodView;
        TextView txtFoodName;

        ItemClickListener listener;

        public void setListener(ItemClickListener listener) {
            this.listener = listener;
        }

        public MyFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFoodView = itemView.findViewById(R.id.imgFoodView);
            txtFoodName = itemView.findViewById(R.id.txtFoodName);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAbsoluteAdapterPosition());
        }
    }

}
