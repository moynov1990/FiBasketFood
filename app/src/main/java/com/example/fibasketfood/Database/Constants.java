package com.example.fibasketfood.Database;

public class Constants {
    public static final String DATABASE_NAME = "orderList.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME  = "MY_RECORDS_TABLE";

    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_QUANTITY = "QUANTITY";
    public static final String C_ITEM = "ITEM";
    public static final String C_ADDED_TIMESTAMP = "ADDED_TIME_STAMP";
    public static final String C_UPDATED_TIMESTAMP = "UPDATED_TIME_STAMP";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_NAME + " TEXT, "
            + C_QUANTITY + " TEXT, "
            + C_ITEM + " TEXT, "
            + C_ADDED_TIMESTAMP + " TEXT, "
            + C_UPDATED_TIMESTAMP  + " TEXT " +     //крапки не треба, 2 пробіли
            ");";
}


//        final String SQL_CREATE_ORDERYLIST_TABLE = "CREATE TABLE " +
//                OrderContract.OrderEntry.TABLE_NAME + " (" +
//                OrderContract.OrderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                OrderContract.OrderEntry.COLUMN_NAME + " TEXT NOT NULL, " +
//                OrderContract.OrderEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, " +
//                OrderContract.OrderEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
//                ");";