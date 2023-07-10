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
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class MyCategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView txtMenuName;

        public MyCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.imgView);
            txtMenuName = itemView.findViewById(R.id.txtMenuName);
        }
    }

}
