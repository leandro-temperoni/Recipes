package com.temperoni.recipes

import android.app.Application
import com.temperoni.recipes.component.*

/**
 * @author Leandro Temperoni
 */
class RecipesApplication : Application(), RecipesComponentProvider {

    val recipesApplicationComponent: RecipesApplicationComponent by lazy {
        DaggerRecipesApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override val recipesComponent: RecipesComponent
        get() = recipesApplicationComponent!!.recipesComponent
}
