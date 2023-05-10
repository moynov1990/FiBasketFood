package com.example.fibasketfood.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMenuName;
    public ImageView imgView;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        txtMenuName = findViewById(R.id.txtMenuName);
    }

    @Override
    public void onClick(View v) {

    }
}
