package com.example.fibasketfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fibasketfood.FoodActivity;
import com.example.fibasketfood.Interface.ItemClickListener;
import com.example.fibasketfood.Model.CategoryModel;
import com.example.fibasketfood.R;

import java.util.List;

public class MyCategoryAdapter extends RecyclerView.Adapter<MyCategoryAdapter.MyCategoryViewHolder> {

    private Context context;
    List<CategoryModel> categoryModelList;

    public MyCategoryAdapter(Context context, List<CategoryModel> categoryModelList) {
        this.context = context;
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public MyCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyCategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCategoryViewHolder holder, int position) {
        Glide.with(context).load(categoryModelList.get(position).getImage()).into(holder.imgView);
        holder.txtMenuName.setText(new StringBuilder().append(categoryModelList.get(position).getName()));

        CategoryModel model = categoryModelList.get(position);
        String categoryId = model.getID();

        holder.setListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int adapterPosition) {
                Intent intent = new Intent(context, FoodActivity.class);
                intent.putExtra("categoryId", categoryId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class MyCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgView;
        TextView txtMenuName;

        ItemClickListener listener;

        public void setListener(ItemClickListener listener) {
            this.listener = listener;
        }

        public MyCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.imgView);
            txtMenuName = itemView.findViewById(R.id.txtMenuName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAbsoluteAdapterPosition());
        }
    }

}
