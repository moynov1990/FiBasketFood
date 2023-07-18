package com.example.fibasketfood.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OrderDBHelper extends SQLiteOpenHelper {

    public OrderDBHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE_NAME);
        onCreate(db);
    }

    public long insertRecord(String name, String quantity, String item, String addedTime, String updatedTime) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constants.C_NAME, name);
        values.put(Constants.C_QUANTITY, quantity);
        values.put(Constants.C_ITEM, item);
        values.put(Constants.C_ADDED_TIMESTAMP, addedTime);
        values.put(Constants.C_UPDATED_TIMESTAMP, updatedTime);

        long id = db.insert(Constants.TABLE_NAME, null, values);

        db.close();

        return id;
    }
}

//
//    public static final String DATABASE_NAME = "orderList.db";
//    public static final int DATABASE_VERSION = 1;
//
//    public OrderDBHelper(FoodActivity context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);          //DATABASE_NAME, DATABASE_VERSION change
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        final String SQL_CREATE_ORDERYLIST_TABLE = "CREATE TABLE " +
//                OrderContract.OrderEntry.TABLE_NAME + " (" +
//                OrderContract.OrderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                OrderContract.OrderEntry.COLUMN_NAME + " TEXT NOT NULL, " +
//                OrderContract.OrderEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, " +
//                OrderContract.OrderEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
//                ");";
//
//        db.execSQL(SQL_CREATE_ORDERYLIST_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + OrderContract.OrderEntry.TABLE_NAME);
//        onCreate(db);
//    }