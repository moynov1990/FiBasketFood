package com.example.fibasketfood.Model;

public class CartModel {
    private String Id, CategoryID, FoodID, Name, Quantity, Item;

    public CartModel(String id, String categoryID, String foodID, String name, String quantity, String item) {
        Id = id;
        CategoryID = categoryID;
        FoodID = foodID;
        Name = name;
        Quantity = quantity;
        Item = item;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public String getFoodID() {
        return FoodID;
    }

    public void setFoodID(String foodID) {
        FoodID = foodID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }
}
