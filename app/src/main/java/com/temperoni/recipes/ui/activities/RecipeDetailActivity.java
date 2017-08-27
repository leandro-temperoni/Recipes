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

import javax.inject.Inject;

import static com.temperoni.recipes.ui.activities.RecipesListActivity.EXTRA_RECIPE_ID;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailView {

    @Inject
    RecipeDetailPresenter presenter;

    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ((RecipesComponentProvider) getApplicationContext()).getRecipesComponent().inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        String recipeId = getIntent().getExtras().getString(EXTRA_RECIPE_ID);

        presenter.setView(this);
        presenter.fetchRecipeDetail(recipeId);
    }

    @Override
    public void displayRecipeDetail(RecipeDetailViewModel viewModel) {
        collapsingToolbar.setTitle(viewModel.getName());
    }
}
