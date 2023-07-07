package com.example.fibasketfood;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Database.OrderContract;
import com.example.fibasketfood.Database.OrderDBHelper;
import com.example.fibasketfood.Model.Food;
import com.example.fibasketfood.ViewHolder.CartAdapter;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private CartAdapter mAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView recyclerCart = findViewById(R.id.recyclerCart);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CartAdapter(this, getAllItems());
        recyclerCart.setAdapter(mAdapter);
    }

    private Cursor getAllItems() {
        return mDatabase.query(
                OrderContract.OrderEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                OrderContract.OrderEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }

    public void onBackPressed() {
        Intent BackToMenu = new Intent(CartActivity.this, FoodActivity.class);
        startActivity(BackToMenu);
        finish();
    }

}