package com.temperoni.recipes.mvp.model;

import com.temperoni.recipes.domain.RecipesManager;
import com.temperoni.recipes.domain.dto.Recipe;
import com.temperoni.recipes.ui.models.RecipeDetailViewModel;

import javax.inject.Inject;

/**
 * @author Leandro Temperoni
 */
public class RecipeDetailModel {

    private RecipesManager recipesManager;

    @Inject
    public RecipeDetailModel(RecipesManager recipesManager) {
        this.recipesManager = recipesManager;
    }

    public void fetchRecipeDetail(String recipeId) {
        recipesManager.fetchRecipeDetail(recipeId);
    }

    public RecipeDetailViewModel getViewModel(Recipe payload) {
        return new RecipeDetailViewModel.Builder(
                payload.getId(),
                payload.getName(),
                payload.getImage(),
                payload.getIntroduction())
                .withIngredients(payload.getIngredients())
                .withSections(payload.getSections())
                .build();
    }
}
