package com.example.fibasketfood;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    private SQLiteDatabase mDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

//        RecyclerView recyclerCart = findViewById(R.id.recyclerCart);
//        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
//        mAdapter = new CartAdapter(this, getAllItems());
//        recyclerCart.setAdapter(mAdapter);
//    }
//
//    private Cursor getAllItems() {
//        return mDatabase.query(
//                OrderContract.OrderEntry.TABLE_NAME,
//                null,
//                null,
//                null,
//                null,
//                null,
//                OrderContract.OrderEntry.COLUMN_TIMESTAMP + " DESC"
//        );
//    }
//
//    public void onBackPressed() {
//        Intent BackToMenu = new Intent(CartActivity.this, FoodActivity.class);
//        startActivity(BackToMenu);
//        finish();
//    }

    }
}