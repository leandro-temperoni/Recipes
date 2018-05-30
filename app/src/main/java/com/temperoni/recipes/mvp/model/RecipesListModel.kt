package com.temperoni.recipes.mvp.model

import com.temperoni.recipes.domain.dto.Recipe
import com.temperoni.recipes.manager.RecipesManager
import com.temperoni.recipes.ui.models.RecipeViewModel
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

    fun getViewModelList(recipes: List<Recipe?>?): List<RecipeViewModel> {
        val recipeViewModels = mutableListOf<RecipeViewModel>()

        recipes?.forEach {
            recipeViewModels.add(RecipeViewModel(it?.id ?: 0,
                    it?.name ?: "",
                    it?.image ?: ""))
        }

        return recipeViewModels
    }
}
