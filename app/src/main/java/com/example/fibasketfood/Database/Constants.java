package com.example.fibasketfood.Database;

public class Constants {
    public static final String DATABASE_NAME = "orderList.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME  = "MY_RECORDS_TABLE";

    public static final String C_ID = "ID";
    public static final String C_CATEGORYID = "CATEGORYID";
    public static final String C_FOODID = "FOODID";
    public static final String C_NAME = "NAME";
    public static final String C_QUANTITY = "QUANTITY";
    public static final String C_ITEM = "ITEM";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_CATEGORYID + " TEXT, "
            + C_FOODID + " TEXT, "
            + C_NAME + " TEXT, "
            + C_QUANTITY + " TEXT, "
            + C_ITEM + " TEXT "
            + "); ";
}
