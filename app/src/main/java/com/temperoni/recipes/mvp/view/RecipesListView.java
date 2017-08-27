package com.temperoni.recipes.mvp.view;

import com.temperoni.recipes.ui.models.RecipeViewModel;

import java.util.List;

/**
 * @author Leandro Temperoni
 */

public interface RecipesListView {

    void displayRecipes(List<RecipeViewModel> recipes);

    void navigateToRecipeDetail(int recipeId);
}
