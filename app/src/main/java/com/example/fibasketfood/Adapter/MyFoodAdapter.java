package com.example.fibasketfood.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fibasketfood.Database.OrderContract;
import com.example.fibasketfood.Interface.ItemClickListener;
import com.example.fibasketfood.Model.FoodModel;
import com.example.fibasketfood.R;

import java.util.List;

public class MyFoodAdapter extends RecyclerView.Adapter<MyFoodAdapter.MyFoodViewHolder> {

    private Context context;
    List<FoodModel> foodModelList;
    int quantity = 1;
    private SQLiteDatabase mDatabase;
    private MyFoodAdapter mAdapter;
    private Cursor mCursor;

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

        FoodModel model = foodModelList.get(position);
        String foodName = model.getName();

        holder.setListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int adapterPosition) {
                String foodNameTV = foodName.toString().trim();
                addItem(foodNameTV, quantity);
            }
        });
    }

    private void addItem(String foodNameTV, int quantity) {
        ContentValues cv = new ContentValues();
        cv.put(OrderContract.OrderEntry.COLUMN_NAME, foodNameTV);
        cv.put(OrderContract.OrderEntry.COLUMN_QUANTITY, quantity);

        mDatabase.insert(OrderContract.OrderEntry.TABLE_NAME, null, cv);
//        mAdapter.swapCursor(getAllItems());
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
