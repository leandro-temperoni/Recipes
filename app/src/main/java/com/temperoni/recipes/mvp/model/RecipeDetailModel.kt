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

    fun getViewModel(payload: Recipe?): RecipeDetailViewModel {
        return RecipeDetailViewModel.Builder(
                payload!!.id,
                payload.name,
                payload.image,
                payload.introduction)
                .withIngredients(payload.ingredients)
                .withInstructions(payload.instructions)
                .build()
    }
}
