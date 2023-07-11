package com.example.fibasketfood.Interface;

import com.example.fibasketfood.Model.FoodModel;

import java.util.List;

public interface FoodLoadListener {
    void onFoodLoadSuccess(List<FoodModel> foodModelList);
    void onFoodLoadFailed(String message);
}
