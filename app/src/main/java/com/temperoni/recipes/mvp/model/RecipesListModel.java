package com.temperoni.recipes.mvp.model;

import com.temperoni.recipes.RecipesManager;

import javax.inject.Inject;

/**
 * @author Leandro Temperoni
 */

public class RecipesListModel {

    private RecipesManager recipesManager;

    @Inject
    public RecipesListModel(RecipesManager recipesManager) {
        this.recipesManager = recipesManager;
    }

    public void fetchData() {
        recipesManager.fetchRecipes();
    }
}
