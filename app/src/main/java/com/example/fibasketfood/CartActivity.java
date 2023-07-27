package com.example.fibasketfood;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Adapter.MyCartAdapter;
import com.example.fibasketfood.Database.Constants;
import com.example.fibasketfood.Database.OrderDBHelper;
import com.example.fibasketfood.Model.CartModel;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerCart;
    ArrayList<CartModel> recordsList;
    MyCartAdapter cartAdapter;
    private OrderDBHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerCart = findViewById(R.id.recyclerCart);

        dbHelper = new OrderDBHelper(this);
        cartAdapter = new MyCartAdapter(CartActivity.this, recordsList);
        recyclerCart.setAdapter(cartAdapter);

        displayData();

    }

    private ArrayList<CartModel> displayData() {
        ArrayList<CartModel> recordsList = new ArrayList<>();
        Cursor cursor = dbHelper.getData();

        if (cursor.moveToFirst()) {
            do {
                CartModel cartModel = new CartModel(
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_NAME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_QUANTITY)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_ITEM))
                );

                recordsList.add(cartModel);
            } while (cursor.moveToNext());
        }
        dbHelper.close();

        return recordsList;
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

