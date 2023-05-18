package com.example.fibasketfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fibasketfood.Model.Category;
import com.example.fibasketfood.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerMenu;
    FirebaseRecyclerOptions<Category> options;
    MenuViewHolder menuViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerMenu = findViewById(R.id.recyclerMenu);
        recyclerMenu.setLayoutManager(new LinearLayoutManager(this));

        options = new FirebaseRecyclerOptions.Builder<Category>().setQuery(FirebaseDatabase.getInstance().getReference().child("category"), Category.class).build();
        menuViewHolder = new MenuViewHolder(options);
        recyclerMenu.setAdapter(menuViewHolder);
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
}