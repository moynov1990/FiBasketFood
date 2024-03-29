package com.example.fibasketfood.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OrderDBHelper extends SQLiteOpenHelper {

    private Context context;
    public OrderDBHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.context = context;
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

        public long insertRecord(String categoryID, String foodID, String name, String quantity, String item) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        Cursor cursor = db.rawQuery("Select * from MY_RECORDS_TABLE where name = ?", new String[] {name});
        if(cursor.getCount()>0) {

            values.put(Constants.C_QUANTITY, quantity);
            values.put(Constants.C_ITEM, item);

            long result = db.update(Constants.TABLE_NAME, values, "name=?", new String[]{name});

            cursor.close();
            db.close();
            return result;

        } else {
            values.put(Constants.C_CATEGORYID, categoryID);
            values.put(Constants.C_FOODID, foodID);
            values.put(Constants.C_NAME, name);
            values.put(Constants.C_QUANTITY, quantity);
            values.put(Constants.C_ITEM, item);

            long result = db.insert(Constants.TABLE_NAME, null, values);

            cursor.close();
            db.close();
            return result;
        }
    }

    public long deleteOneRecord(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(Constants.TABLE_NAME, "id=?", new String[]{id});
        db.close();
        return result;
    }


    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "Select * from " + Constants.TABLE_NAME + " ORDER BY " + Constants.C_CATEGORYID + " DESC";
//        String qry = "Select * from MY_RECORDS_TABLE ORDER BY ID DESC";
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;
    }
}


//    public long insertRecord(String name, String quantity, String item) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
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


//    public long insertRecord(String name, String quantity, String item) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//
//        values.put(Constants.C_NAME, name);
//        values.put(Constants.C_QUANTITY, quantity);
//        values.put(Constants.C_ITEM, item);
//
//        long id = db.insert(Constants.TABLE_NAME, null, values);
//
//        db.close();
//
//        return id;
//    }



