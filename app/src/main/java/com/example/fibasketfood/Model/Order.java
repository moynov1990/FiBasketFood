package com.example.fibasketfood.Model;

public class Order {

    public static String ProductID;
    public static String ProductName;

    public Order() {
    }

    public Order(String productID, String productName) {
        ProductID = productID;
        ProductName = productName;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }
}
