package com.tiago.movies_api.dtos;

import com.tiago.movies_api.models.Category;

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
        Category category = new Category();

        category.setName(this.name);

        return category;
    }
}
