package com.example.fibasketfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.fibasketfood.Interface.ItemClickListener;
import com.example.fibasketfood.Model.Food;
import com.example.fibasketfood.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity implements ItemClickListener, FoodViewHolder.FoodListClickListener {

    RecyclerView recyclerFood;
    FirebaseRecyclerOptions<Food> fOptions;
    FoodViewHolder foodViewHolder;
    ImageView imgFoodView;
    String categoryID = "";
//    DatabaseReference foodList;
    Button btnCart;

    private List<Food> foodsInCart;
    private int totalItemInCart = 0;


    ElegantNumberButton numBtnFood; //FoodViewHolder.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Food foodModel = null;

        btnCart = findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodModel.setFoodsInCart(foodsInCart);

                Intent cartActivity = new Intent(FoodActivity.this, CartActivity.class);
                cartActivity.putExtra("FoodModel", foodModel);
                startActivity(cartActivity);
                finish();
            }
        });

        recyclerFood = findViewById(R.id.recyclerFood);
        recyclerFood.setLayoutManager(new LinearLayoutManager(this));

//        foodList = FirebaseDatabase.getInstance().getReference("food");

        categoryID = getIntent().getStringExtra("CategoryID");
        fOptions = new FirebaseRecyclerOptions.Builder<Food>().setQuery(FirebaseDatabase.getInstance().getReference().child("food").orderByChild("MenuID").equalTo(categoryID), Food.class).build();
        foodViewHolder = new FoodViewHolder(fOptions, this, this);
        recyclerFood.setAdapter(foodViewHolder);

    }

    @Override
    protected void onStart() {
        super.onStart();
        foodViewHolder.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        foodViewHolder.stopListening();
    }

    public void onBackPressed() {
        Intent BackToMenu = new Intent(FoodActivity.this, HomeActivity.class);
        startActivity(BackToMenu);
        finish();
    }

    @Override
    public void onClick(int position) {

    }


    @Override
    public void onAddToCartClick(Food food) {
        if(foodsInCart == null) {
            foodsInCart = new ArrayList<>();
        }
        foodsInCart.add(food);
        totalItemInCart = 0;
        for(Food f : foodsInCart) {
            totalItemInCart = totalItemInCart + f.getTotalInCart();
        }
        btnCart.setText("Checkout (" +totalItemInCart +") items");
    }

    @Override
    public void onUpdateCartClick(Food food) {

    }

    @Override
    public void onRemoveFromCartClick(Food food) {

    }
}