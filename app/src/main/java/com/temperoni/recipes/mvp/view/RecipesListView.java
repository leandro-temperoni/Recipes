package com.temperoni.recipes.mvp.view;

import android.view.View;

import com.temperoni.recipes.ui.models.RecipeViewModel;

import java.util.List;

/**
 * @author Leandro Temperoni
 */

public interface RecipesListView {

    void displayRecipes(List<RecipeViewModel> recipes);

    void navigateToRecipeDetail(String recipeId, View sharedView);
}
