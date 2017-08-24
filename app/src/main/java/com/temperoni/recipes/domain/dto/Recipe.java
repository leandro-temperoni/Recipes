package com.temperoni.recipes.domain.dto;

import java.util.List;

/**
 * @author Leandro Temperoni
 */

public class Recipe {

    private int id;
    private String image;
    private List<Ingredient> ingredients;
    private String introduction;
    private String name;
    private List<Section> sections;

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getName() {
        return name;
    }

    public List<Section> getSections() {
        return sections;
    }
}
