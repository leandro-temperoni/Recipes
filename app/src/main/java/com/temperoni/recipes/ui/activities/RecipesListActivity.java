package com.temperoni.recipes.ui.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.temperoni.recipes.R;
import com.temperoni.recipes.component.RecipesComponentProvider;
import com.temperoni.recipes.mvp.presenter.RecipesListPresenter;
import com.temperoni.recipes.mvp.view.RecipesListView;
import com.temperoni.recipes.ui.adapters.RecipesListAdapter;
import com.temperoni.recipes.ui.models.RecipeViewModel;
import com.temperoni.recipes.ui.views.VerticalSpaceItemDecoration;

import java.util.List;

import javax.inject.Inject;

public class RecipesListActivity extends AppCompatActivity implements RecipesListView {

    static final String EXTRA_RECIPE_ID = "EXTRA_RECIPE_ID";

    private RecyclerView mRecipesView;
    private RecipesListAdapter mAdapter;

    @Inject
    RecipesListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((RecipesComponentProvider) getApplicationContext()).getRecipesComponent().inject(this);

        mRecipesView = (RecyclerView) findViewById(R.id.recipes_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecipesView.setLayoutManager(mLayoutManager);
        mRecipesView.addItemDecoration(new VerticalSpaceItemDecoration(30));
        mRecipesView.setItemAnimator(new DefaultItemAnimator());
        mRecipesView.setHasFixedSize(true);
        mAdapter = new RecipesListAdapter();
        mAdapter.setListener(presenter);
        mRecipesView.setAdapter(mAdapter);

        presenter.setView(this);
        presenter.fetchData();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.register();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unregister();
    }

    @Override
    public void displayRecipes(List<RecipeViewModel> recipes) {
        mAdapter.setData(recipes);
    }

    @Override
    public void navigateToRecipeDetail(String recipeId, View sharedView) {
        Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtra(EXTRA_RECIPE_ID, recipeId);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, sharedView, "agreedName1");

        startActivity(intent, options.toBundle());
    }
}
