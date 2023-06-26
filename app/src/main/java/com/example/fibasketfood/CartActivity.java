package com.example.fibasketfood;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Model.Food;
import com.example.fibasketfood.ViewHolder.CartViewHolder;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerCart;
    CartViewHolder cartViewHolder;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerCart = findViewById(R.id.recyclerCart);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));

        Food orderModel = getIntent().getParcelableExtra("OrderModel");

        initRecyclerView(orderModel);
    }

    private void initRecyclerView(Food orderModel) {
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        cartViewHolder = new CartViewHolder(orderModel.getFoods());
        recyclerCart.setAdapter(cartViewHolder);
    }

    public void onBackPressed() {
        Intent BackToMenu = new Intent(CartActivity.this, FoodActivity.class);
        startActivity(BackToMenu);
        finish();
    }


}