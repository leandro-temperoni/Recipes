package com.temperoni.recipes.component

import com.temperoni.recipes.ui.activities.RecipeDetailActivity
import com.temperoni.recipes.ui.activities.RecipesListActivity

import dagger.Subcomponent

/**
 * @author Leandro Temperoni
 */
@Subcomponent
interface RecipesComponent {

    fun inject(recipesListActivity: RecipesListActivity)

    fun inject(recipeDetailActivity: RecipeDetailActivity)
}
