package com.temperoni.recipes.mvp.view

import android.view.View

import com.temperoni.recipes.ui.models.RecipeViewModel

/**
 * @author Leandro Temperoni
 */

interface RecipesListView {

    fun displayLoading()

    fun displayError()

    fun displayEmptyState()

    fun displayRecipes(data: List<RecipeViewModel>)

    fun navigateToRecipeDetail(recipeId: String, imageUrl: String, sharedView: View)
}
