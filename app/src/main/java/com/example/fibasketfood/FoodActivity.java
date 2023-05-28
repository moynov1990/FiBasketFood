package com.example.fibasketfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.fibasketfood.Model.Food;
import com.example.fibasketfood.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FoodActivity extends AppCompatActivity {

    RecyclerView recyclerFood;
    FirebaseRecyclerOptions<Food> fOptions;
    FoodViewHolder foodViewHolder;
    ImageView imgFoodView;
    String categoryID = "";
//    DatabaseReference foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        recyclerFood = findViewById(R.id.recyclerFood);
        recyclerFood.setLayoutManager(new LinearLayoutManager(this));

//        foodList = FirebaseDatabase.getInstance().getReference("food");

        categoryID = getIntent().getStringExtra("CategoryID");
        fOptions = new FirebaseRecyclerOptions.Builder<Food>().setQuery(FirebaseDatabase.getInstance().getReference().child("food").orderByChild("MenuID").equalTo(categoryID), Food.class).build();
        foodViewHolder = new FoodViewHolder(fOptions);
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

}