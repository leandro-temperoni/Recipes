package com.temperoni.recipes.component;

import com.temperoni.recipes.ui.activities.RecipesListActivity;

import dagger.Subcomponent;

/**
 * @author Leandro Temperoni
 */
@Subcomponent
public interface RecipesComponent {

    void inject(RecipesListActivity recipesListActivity);
}
