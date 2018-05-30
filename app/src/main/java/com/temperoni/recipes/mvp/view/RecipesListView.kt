package com.temperoni.recipes.mvp.view

import android.view.View

import com.temperoni.recipes.ui.models.RecipeViewModel

/**
 * @author Leandro Temperoni
 */

interface RecipesListView {

    fun displayRecipes(recipes: List<RecipeViewModel>)

    fun navigateToRecipeDetail(recipeId: String, imageUrl: String, sharedView: View)
}
