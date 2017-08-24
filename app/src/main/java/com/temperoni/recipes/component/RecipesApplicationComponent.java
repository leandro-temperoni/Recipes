package com.temperoni.recipes.component;

import android.content.Context;

import com.temperoni.recipes.RecipesApplication;
import com.temperoni.recipes.ui.activities.RecipesListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Leandro Temperoni
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        RecipesModule.class})
public interface RecipesApplicationComponent {

    RecipesComponent getRecipesComponent();
}
