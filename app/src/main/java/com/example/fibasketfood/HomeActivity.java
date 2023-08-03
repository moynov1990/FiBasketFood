package com.example.fibasketfood;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Adapter.MyCategoryAdapter;
import com.example.fibasketfood.Interface.CartLoadListener;
import com.example.fibasketfood.Interface.CategoryLoadListener;
import com.example.fibasketfood.Model.CartModel;
import com.example.fibasketfood.Model.CategoryModel;
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

public class HomeActivity extends AppCompatActivity implements CategoryLoadListener {

    RecyclerView recyclerMenu;
    DrawerLayout drawerLayout;
    ImageView imgMenu;
    FrameLayout btnCart;
    NotificationBadge badge;
    CategoryLoadListener categoryLoadListener;
    CartLoadListener cartLoadListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawerLayout);
        recyclerMenu = findViewById(R.id.recyclerMenu);

        init();
        loadCategoryFromFirebase();

//        imgMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
//
//        btnCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cartActivity = new Intent(HomeActivity.this, CartActivity.class);
//                startActivity(cartActivity);
//                finish();
//            }
//        });
    }

    private void loadCategoryFromFirebase() {
        List<CategoryModel> categoryModels = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("category");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for(DataSnapshot categorySnapshot:snapshot.getChildren()) {
                        CategoryModel categoryModel = categorySnapshot.getValue(CategoryModel.class);
                        categoryModel.setKey(categorySnapshot.getKey());
                        categoryModels.add(categoryModel);
                    }
                    categoryLoadListener.onCategoryLoadSuccess(categoryModels);
                }
                else categoryLoadListener.onCategoryLoadFailed("can't find category");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                categoryLoadListener.onCategoryLoadFailed(error.getMessage());
            }
        });
    }

    private void init() {
        categoryLoadListener = this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerMenu.setLayoutManager(gridLayoutManager);
        recyclerMenu.addItemDecoration(new SpaceItemDecoration());
    }

    @Override
    public void onCategoryLoadSuccess(List<CategoryModel> categoryModelList) {
        MyCategoryAdapter adapter = new MyCategoryAdapter(this, categoryModelList);
        recyclerMenu.setAdapter(adapter);
    }

    @Override
    public void onCategoryLoadFailed(String message) {
        Snackbar.make(drawerLayout, message, Snackbar.LENGTH_LONG).show();
    }


    public void onBackPressed() {
        Intent BackToMain = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(BackToMain);
        finish();
    }

}


