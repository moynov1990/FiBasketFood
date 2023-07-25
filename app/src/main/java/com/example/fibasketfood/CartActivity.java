package com.example.fibasketfood;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Adapter.MyCartAdapter;
import com.example.fibasketfood.Database.Constants;
import com.example.fibasketfood.Database.OrderDBHelper;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerCart;

    private OrderDBHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerCart = findViewById(R.id.recyclerCart);

        dbHelper = new OrderDBHelper(this);

        loadRecords();

    }

    private void loadRecords() {
        MyCartAdapter cartAdapter = new MyCartAdapter(CartActivity.this,
                dbHelper.getAllRecords(Constants.C_ID + " DESC"));

        recyclerCart.setAdapter(cartAdapter);
    }

}