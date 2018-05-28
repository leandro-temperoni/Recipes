package com.temperoni.recipes.component

import dagger.Component
import javax.inject.Singleton

/**
 * @author Leandro Temperoni
 */
@Singleton
@Component(modules = [(ApplicationModule::class), (RecipesModule::class)])
interface RecipesApplicationComponent {

    val recipesComponent: RecipesComponent
}
