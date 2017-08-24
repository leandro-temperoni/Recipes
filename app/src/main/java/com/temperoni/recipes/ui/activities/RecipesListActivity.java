package com.temperoni.recipes.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.temperoni.recipes.R;
import com.temperoni.recipes.component.RecipesComponentProvider;
import com.temperoni.recipes.domain.dto.Recipe;
import com.temperoni.recipes.mvp.presenter.RecipesListPresenter;
import com.temperoni.recipes.mvp.view.RecipesListView;
import com.temperoni.recipes.ui.adapters.RecipesListAdapter;
import com.temperoni.recipes.ui.views.VerticalSpaceItemDecoration;

import java.util.List;

import javax.inject.Inject;

public class RecipesListActivity extends AppCompatActivity implements RecipesListView {

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
    public void displayRecipes(List<Recipe> recipes) {
        mAdapter.setData(recipes);
    }
}
