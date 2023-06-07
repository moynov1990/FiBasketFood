package com.example.fibasketfood.Model;

public class Cart {
    private String Image, FoodID, Name;

    public Cart() {

    }

    public Cart(String image, String foodID, String name) {
        Image = image;
        FoodID = foodID;
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getFoodID() {
        return FoodID;
    }

    public void setfoodID(String foodID) {
        FoodID = foodID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
