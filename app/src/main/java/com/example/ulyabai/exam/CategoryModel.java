package com.example.ulyabai.exam;

public class CategoryModel {
    String categoryName;

    public CategoryModel( String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryModel(){}

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
