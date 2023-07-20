package com.example.fibasketfood.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public long updateRecord(String name, String quantity, String item) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constants.C_QUANTITY, quantity);
        values.put(Constants.C_ITEM, item);

        Cursor cursor = db.rawQuery("Select * from MY_RECORDS_TABLE where name = ?", new String[] {name});
        if(cursor.getCount()>0) {
            long id = db.update(Constants.TABLE_NAME, values, "name=?", new String[]{name});
            if (id == -1) {
                return -1;
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }
}

