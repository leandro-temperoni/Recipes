package com.temperoni.recipes;

import android.app.Application;
import com.temperoni.recipes.component.ApplicationModule;
import com.temperoni.recipes.component.DaggerRecipesApplicationComponent;
import com.temperoni.recipes.component.RecipesApplicationComponent;
import com.temperoni.recipes.component.RecipesComponent;
import com.temperoni.recipes.component.RecipesComponentProvider;

/**
 * @author Leandro Temperoni
 */

public class RecipesApplication extends Application implements RecipesComponentProvider {

    private RecipesApplicationComponent recipesApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeInjector();
    }

    private void initializeInjector() {
        recipesApplicationComponent = DaggerRecipesApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public RecipesApplicationComponent getRecipesApplicationComponent() {
        return recipesApplicationComponent;
    }

    @Override
    public RecipesComponent getRecipesComponent() {
        return recipesApplicationComponent.getRecipesComponent();
    }
}
