package com.example.fibasketfood;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Adapter.MyFoodAdapter;
import com.example.fibasketfood.Database.OrderDBHelper;
import com.example.fibasketfood.Interface.FoodLoadListener;
import com.example.fibasketfood.Model.FoodModel;
import com.example.fibasketfood.utils.SpaceItemDecoration;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity implements FoodLoadListener {

    RecyclerView recyclerFood;
    DrawerLayout drawerLayout;
    ImageView imgFoodView;
    FrameLayout btnCart;
    NotificationBadge badge;
    String categoryID = "";
    FoodLoadListener foodLoadListener;

    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        recyclerFood = findViewById(R.id.recyclerFood);

        OrderDBHelper dbHelper = new OrderDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        categoryID = getIntent().getStringExtra("CategoryID");

        init();
        loadFoodFromFirebase();

    }

    private void loadFoodFromFirebase() {
        List<FoodModel> foodModels = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("food");
        ref.orderByChild("CategoryID").equalTo(categoryID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            for(DataSnapshot foodSnapshot:snapshot.getChildren()) {
                                FoodModel foodModel = foodSnapshot.getValue(FoodModel.class);
                                foodModel.setKey(foodSnapshot.getKey());
                                foodModels.add(foodModel);
                            }
                            foodLoadListener.onFoodLoadSuccess(foodModels);
                        }
                        else foodLoadListener.onFoodLoadFailed("can't find category");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        foodLoadListener.onFoodLoadFailed(error.getMessage());
                    }
        });
    }

    private void init() {
        foodLoadListener = this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerFood.setLayoutManager(gridLayoutManager);
        recyclerFood.addItemDecoration(new SpaceItemDecoration());
    }


    @Override
    public void onFoodLoadSuccess(List<FoodModel> foodModelList) {
        MyFoodAdapter adapter = new MyFoodAdapter(this, foodModelList);
        recyclerFood.setAdapter(adapter);
    }

    @Override
    public void onFoodLoadFailed(String message) {
        Snackbar.make(drawerLayout, message, Snackbar.LENGTH_LONG).show();
    }

    public void onBackPressed() {
        Intent BackToMenu = new Intent(FoodActivity.this, HomeActivity.class);
        startActivity(BackToMenu);
        finish();
    }
}