package com.example.fibasketfood.Model;

public class Food {
    private String Image, MenuID, Name;

    public Food() {

    }

    public Food(String image, String menuID, String name) {
        Image = image;
        MenuID = menuID;
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMenuID() {
        return MenuID;
    }

    public void setMenuID(String menuID) {
        MenuID = menuID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
