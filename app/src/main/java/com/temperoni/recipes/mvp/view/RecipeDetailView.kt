package com.temperoni.recipes.mvp.view

import com.temperoni.recipes.ui.models.RecipeDetailViewModel

/**
 * @author Leandro Temperoni
 */

interface RecipeDetailView {

    fun displayRecipeDetail(viewModel: RecipeDetailViewModel?)
}
