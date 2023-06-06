package com.example.fibasketfood.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.fibasketfood.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {


    private static final String DB_NAME = "fibasketDB.db";
    private static final int DB_VER = 1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }


    public List<Order> getCarts() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqSelect = {"ProductID", "ProductName"};
        String sqlTable = "OrderOffline";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqSelect, null, null, null, null, null);

        final List<Order> result = new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                result.add(new Order(c.getString(c.getColumnIndex("ProductID")),
                                     c.getString(c.getColumnIndex("ProductName")))
                );
            } while (c.moveToNext());
        }

        return result;
    }

    public void addToCart (Order order) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderOffline (ProductID, ProductName) VALUES ('%s', '%s');",
                order.getProductID(),
                order.getProductName());
        db.execSQL(query);
    }
}



//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if exists OrderOffline");
//        onCreate(db);
//    }
//
//    public void insertData(Order order ) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("productID", order.getProductID());
//        contentValues.put("productName", order.getProductName());
//        long OrderOffline = sqLiteDatabase.insert("OrderOffline", null, contentValues);
//    }
