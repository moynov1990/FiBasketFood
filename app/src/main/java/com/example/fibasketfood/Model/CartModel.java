package com.example.fibasketfood.Model;

public class CartModel {
    private String Id, Name, Quantity, Item;

    public CartModel(String id, String name, String quantity, String item) {
        Id = id;
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
