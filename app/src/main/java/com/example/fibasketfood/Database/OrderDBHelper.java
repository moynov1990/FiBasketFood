package com.example.fibasketfood.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.fibasketfood.Model.CartModel;

import java.util.ArrayList;

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

    public long insertRecord(String name, String quantity, String item) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constants.C_NAME, name);
        values.put(Constants.C_QUANTITY, quantity);
        values.put(Constants.C_ITEM, item);

        long id = db.insert(Constants.TABLE_NAME, null, values);

        db.close();

        return id;
    }

//    public long insertRecord(String name, String quantity, String item) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//
//
//        Cursor cursor = db.rawQuery("Select * from MY_RECORDS_TABLE where name = ?", new String[] {name});
//        if(cursor.getCount()>0) {
//
//            values.put(Constants.C_QUANTITY, quantity);
//            values.put(Constants.C_ITEM, item);
//
//            long id = db.update(Constants.TABLE_NAME, values, "name=?", new String[]{name});
//
//        } else {
//            values.put(Constants.C_NAME, name);
//            values.put(Constants.C_QUANTITY, quantity);
//            values.put(Constants.C_ITEM, item);
//
//            long id = db.insert(Constants.TABLE_NAME, null, values);
//        }
//        cursor.close();
//        db.close();
//        return 0;
//    }

    public ArrayList<CartModel> getAllRecords(String orderBy) {
        ArrayList<CartModel> recordsList = new ArrayList<>();
        String selectQuery = "Select * from " + Constants.TABLE_NAME + " ORDER BY " + orderBy; ////////;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

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
        db.close();

        return recordsList;
    }

}

