package com.example.fibasketfood.Database;

import android.provider.BaseColumns;

public class OrderContract {

    public OrderContract() {

    }
    public static final class OrderEntry implements BaseColumns {
        public static final String TABLE_NAME = "orderList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
