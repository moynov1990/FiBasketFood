package com.example.fibasketfood;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Adapter.MyCartAdapter;
import com.example.fibasketfood.Database.OrderDBHelper;
import com.example.fibasketfood.Model.CartModel;

import java.util.ArrayList;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity {

    Button checkoutBtn;
    String orderBy;

    RecyclerView recyclerCart;
    ArrayList<CartModel> recordsList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerCart = findViewById(R.id.recyclerCart);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = new OrderDBHelper(this).getData();

        while (cursor.moveToNext()) {
            CartModel obj = new CartModel(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            recordsList.add(obj);

            orderBy = getIntent().getStringExtra("orderBy");
        }

        MyCartAdapter cartAdapter = new MyCartAdapter(this, recordsList);
        recyclerCart.setAdapter(cartAdapter);

        checkoutBtn = findViewById(R.id.checkoutBtn);

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recordsList.size() == 0) {
                    Toast.makeText(CartActivity.this, "Кошик пустий" , Toast.LENGTH_SHORT).show();
                } else {
                    submitOrder();
                }
            }
        });

    }

    private void submitOrder() {
        String timestamp = ""+System.currentTimeMillis();

        HashMap<String, String> hashMap = new HashMap<> ();
        hashMap.put("orderId", ""+timestamp);
        hashMap.put("orderTime", ""+timestamp);
        hashMap.put("orderStatus", "В Процесі");
        hashMap.put("orderBy", ""+orderBy);
    }
}

