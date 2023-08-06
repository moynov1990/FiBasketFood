package com.example.fibasketfood;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Adapter.MyCartAdapter;
import com.example.fibasketfood.Database.OrderDBHelper;
import com.example.fibasketfood.Model.CartModel;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

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
        }

        MyCartAdapter cartAdapter = new MyCartAdapter(this, recordsList);
        recyclerCart.setAdapter(cartAdapter);

//        displayData();
    }


//        if(cursor.getCount()==0) {
//            Toast.makeText(CartActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        else {
//            while (cursor.moveToNext()) {
//                recordsList.add(cursor.getString());
//            }
//        }

}

