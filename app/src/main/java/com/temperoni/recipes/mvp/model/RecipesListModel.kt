package com.temperoni.recipes.mvp.model

import com.temperoni.recipes.domain.dto.Recipe
import com.temperoni.recipes.manager.RecipesManager
import com.temperoni.recipes.ui.models.RecipeViewModel
import java.util.*
import javax.inject.Inject

/**
 * @author Leandro Temperoni
 */
class RecipesListModel

    @Inject
    constructor(private val recipesManager: RecipesManager) {

    fun fetchData() {
        recipesManager.fetchRecipes()
    }

    fun getViewModelList(recipes: List<Recipe>?): List<RecipeViewModel> {
        val recipeViewModels = ArrayList<RecipeViewModel>()

        for ((id, image, _, _, name) in recipes!!) {
            val recipeViewModel = RecipeViewModel(
                    id,
                    name,
                    image)

            recipeViewModels.add(recipeViewModel)
        }

        return recipeViewModels
    }
}
