package com.temperoni.recipes.ui.models;

/**
 * @author Leandro Temperoni
 */
public class RecipeViewModel {

    private int id;
    private String name;
    private String imageUrl;

    public RecipeViewModel(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
