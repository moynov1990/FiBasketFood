package com.example.fibasketfood.Interface;

import com.example.fibasketfood.Model.CategoryModel;

import java.util.List;

public interface CategoryLoadListener {
    void onCategoryLoadSuccess(List<CategoryModel> categoryModelList);
    void onCategoryLoadFailed(String message);
}
