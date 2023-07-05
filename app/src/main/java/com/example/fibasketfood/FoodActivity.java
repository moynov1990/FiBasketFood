package com.example.fibasketfood;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.fibasketfood.Database.OrderDBHelper;
import com.example.fibasketfood.Interface.ItemClickListener;
import com.example.fibasketfood.Model.Food;
import com.example.fibasketfood.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class FoodActivity extends AppCompatActivity implements ItemClickListener {

    RecyclerView recyclerFood;
    FirebaseRecyclerOptions<Food> fOptions;
    FoodViewHolder foodViewHolder;
    ImageView imgFoodView;
    String categoryID = "";
    Button btnCart;

    private SQLiteDatabase mDatabase;


    private int totalItemInCart = 0;


    ElegantNumberButton numBtnFood; //FoodViewHolder.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        OrderDBHelper dbHelper = new OrderDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();


        btnCart = findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cartActivity = new Intent(FoodActivity.this, CartActivity.class);
                startActivity(cartActivity);
                finish();
            }
        });

        recyclerFood = findViewById(R.id.recyclerFood);
        recyclerFood.setLayoutManager(new LinearLayoutManager(this));

//        foodList = FirebaseDatabase.getInstance().getReference("food");

        categoryID = getIntent().getStringExtra("CategoryID");
        fOptions = new FirebaseRecyclerOptions.Builder<Food>().setQuery(FirebaseDatabase.getInstance().getReference().child("food").orderByChild("MenuID").equalTo(categoryID), Food.class).build();
        foodViewHolder = new FoodViewHolder(fOptions, this);
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


}