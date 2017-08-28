package com.temperoni.recipes.ui.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.temperoni.recipes.R;
import com.temperoni.recipes.component.RecipesComponentProvider;
import com.temperoni.recipes.mvp.presenter.RecipeDetailPresenter;
import com.temperoni.recipes.mvp.view.RecipeDetailView;
import com.temperoni.recipes.ui.models.RecipeDetailViewModel;
import com.temperoni.recipes.ui.views.IngredientsSection;

import javax.inject.Inject;

import static com.temperoni.recipes.ui.activities.RecipesListActivity.EXTRA_RECIPE_ID;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailView {

    @Inject
    RecipeDetailPresenter presenter;

    private CollapsingToolbarLayout collapsingToolbar;
    private IngredientsSection ingredientsSection;

    private String recipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ((RecipesComponentProvider) getApplicationContext()).getRecipesComponent().inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        ingredientsSection = (IngredientsSection) findViewById(R.id.ingredients_section);

        recipeId = getIntent().getExtras().getString(EXTRA_RECIPE_ID);

        presenter.setView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.register();
        presenter.fetchRecipeDetail(recipeId);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unregister();
    }

    @Override
    public void displayRecipeDetail(RecipeDetailViewModel viewModel) {
        collapsingToolbar.setTitle(viewModel.getName());
        ingredientsSection.setIngredients(viewModel.getIngredients());
    }
}
