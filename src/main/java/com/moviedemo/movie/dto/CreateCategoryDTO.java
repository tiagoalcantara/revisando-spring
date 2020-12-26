package com.moviedemo.movie.dto;

import com.moviedemo.movie.model.Category;

import javax.validation.constraints.NotNull;

public class CreateCategoryDTO {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category toCategory() {
        Category newCategory = new Category();
        newCategory.setName(this.name);

        return newCategory;
    }
}
