package com.temperoni.recipes.mvp.model

import com.temperoni.recipes.domain.dto.Recipe
import com.temperoni.recipes.manager.RecipesManager
import com.temperoni.recipes.ui.models.RecipeDetailViewModel
import javax.inject.Inject

/**
 * @author Leandro Temperoni
 */
class RecipeDetailModel

    @Inject
    constructor(private val recipesManager: RecipesManager) {

    fun fetchRecipeDetail(recipeId: String) {
        recipesManager.fetchRecipeDetail(recipeId)
    }

    fun getViewModel(payload: Recipe?): RecipeDetailViewModel? {
        return if (payload != null) {
            RecipeDetailViewModel.Builder(
                    payload.id ?: 0,
                    payload.name ?: "",
                    payload.image ?: "",
                    payload.introduction ?: "")
                    .withIngredients(payload.ingredients ?: listOf())
                    .withInstructions(payload.instructions ?: listOf())
                    .build()
        } else null
    }
}
