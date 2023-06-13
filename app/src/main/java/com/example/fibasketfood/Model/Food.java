package com.example.fibasketfood.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Food implements Parcelable {

    private String Image, MenuID, Name;
    private int totalInCart;
    private List<Food> foods;

    public int getTotalInCart() {
        return totalInCart;
    }

    public void setTotalInCart(int totalInCart) {
        this.totalInCart = totalInCart;
    }

    public Food() {

    }

    protected Food(Parcel in) {
        MenuID = in.readString();
        Name = in.readString();
        totalInCart = in.readInt();
    }


    public List<Food> getMenus() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }


    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeInt(totalInCart);
    }
}
