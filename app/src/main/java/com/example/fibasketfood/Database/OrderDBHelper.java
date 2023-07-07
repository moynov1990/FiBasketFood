package com.example.fibasketfood.Database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fibasketfood.CartActivity;

public class OrderDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "orderList.db";
    public static final int DATABASE_VERSION = 1;

    public OrderDBHelper(CartActivity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);          //DATABASE_NAME, DATABASE_VERSION change
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_ORDERYLIST_TABLE = "CREATE TABLE " +
                OrderContract.OrderEntry.TABLE_NAME + " (" +
                OrderContract.OrderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                OrderContract.OrderEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                OrderContract.OrderEntry.COLUMN_AMOUNT + " INTEGER NOT NULL, " +
                OrderContract.OrderEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_ORDERYLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + OrderContract.OrderEntry.TABLE_NAME);
        onCreate(db);
    }
}