package com.example.fibasketfood.Interface;

import com.example.fibasketfood.Model.CartModel;

import java.util.List;

public interface CartLoadListener {
    void onCartLoadSuccess(List<CartModel> cartModelList);
    void onCartLoadFailed(String message);
}
