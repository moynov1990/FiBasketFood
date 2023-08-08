package com.example.fibasketfood;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibasketfood.Adapter.MyCartAdapter;
import com.example.fibasketfood.Database.OrderDBHelper;
import com.example.fibasketfood.Model.CartModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        }

        orderBy = getIntent().getStringExtra("orderBy");

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

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User").child("123").child("Orders");   // .child("123") - foodId
        ref.child(timestamp).setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        for (int i = 0; i < recordsList.size(); i++) {
                            String foodId = recordsList.get(i).getId();
                            String foodName = recordsList.get(i).getName();
                            String quantity = recordsList.get(i).getQuantity();
                            String item = recordsList.get(i).getItem();

                            HashMap<String, String> hashMap1 = new HashMap<> ();
                            hashMap1.put("foodId", foodId);
                            hashMap1.put("foodName", foodName);
                            hashMap1.put("quantity", quantity);
                            hashMap1.put("item", item);

                            ref.child(timestamp).child("Items").setValue(hashMap1);
                        }
                        Toast.makeText(CartActivity.this, "Замовлення розміщено", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CartActivity.this, "Помилка розміщенення замовлення", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

