package com.example.fibasketfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fibasketfood.Interface.ItemClickListener;
import com.example.fibasketfood.Model.Category;
import com.example.fibasketfood.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity implements ItemClickListener {

    RecyclerView recyclerMenu;
    FirebaseRecyclerOptions<Category> mOptions;
    MenuViewHolder menuViewHolder;
    DrawerLayout drawerLayout;
    ImageView imgMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerMenu = findViewById(R.id.recyclerMenu);
        recyclerMenu.setLayoutManager(new LinearLayoutManager(this));

        mOptions = new FirebaseRecyclerOptions.Builder<Category>().setQuery(FirebaseDatabase.getInstance().getReference().child("category"), Category.class).build();
        menuViewHolder = new MenuViewHolder(mOptions, this);
        recyclerMenu.setAdapter(menuViewHolder);

        drawerLayout = findViewById(R.id.drawerLayout);
        imgMenu = findViewById(R.id.imgMenu);

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        menuViewHolder.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        menuViewHolder.stopListening();
    }

    @Override
    public void onClick(int position) {
        Intent foodActivity = new Intent(HomeActivity.this, FoodActivity.class);
        foodActivity.putExtra("CategoryID", menuViewHolder.getRef(position).getKey());
        startActivity(foodActivity);
        finish();
    }

    public void onBackPressed() {
        Intent BackToMain = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(BackToMain);
        finish();
    }
}