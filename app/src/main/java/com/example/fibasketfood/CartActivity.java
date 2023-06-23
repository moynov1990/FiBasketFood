package com.example.fibasketfood;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Interface.ItemClickListener;
import com.example.fibasketfood.Model.Cart;
import com.example.fibasketfood.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class CartActivity extends AppCompatActivity implements ItemClickListener {

    RecyclerView recyclerCart;
    FirebaseRecyclerOptions<Cart> cOptions;
    CartViewHolder cartViewHolder;


    String foodID = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerCart = findViewById(R.id.recyclerCart);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));

//        foodList = FirebaseDatabase.getInstance().getReference("food");

        foodID = getIntent().getStringExtra("FoodID");
        cOptions = new FirebaseRecyclerOptions.Builder<Cart>().setQuery(FirebaseDatabase.getInstance().getReference().child("food").orderByChild("FoodID").equalTo(foodID), Cart.class).build();
        cartViewHolder = new CartViewHolder(cOptions, this);
        recyclerCart.setAdapter(cartViewHolder);

    }

    @Override
    protected void onStart() {
        super.onStart();
        cartViewHolder.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cartViewHolder.stopListening();
    }

    public void onBackPressed() {
        Intent BackToMenu = new Intent(CartActivity.this, FoodActivity.class);
        startActivity(BackToMenu);
        finish();
    }


    @Override
    public void onClick(int position) {

    }
}