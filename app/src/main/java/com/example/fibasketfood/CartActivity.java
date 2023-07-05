package com.example.fibasketfood;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Model.Food;
import com.example.fibasketfood.ViewHolder.CartViewHolder;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerCart;
    CartViewHolder cartViewHolder;
    private List<Food> menuList = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

    }

    public void onBackPressed() {
        Intent BackToMenu = new Intent(CartActivity.this, FoodActivity.class);
        startActivity(BackToMenu);
        finish();
    }

}