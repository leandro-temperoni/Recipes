package com.temperoni.recipes.ui.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.temperoni.recipes.R;
import com.temperoni.recipes.component.RecipesComponentProvider;
import com.temperoni.recipes.mvp.presenter.RecipeDetailPresenter;
import com.temperoni.recipes.mvp.view.RecipeDetailView;
import com.temperoni.recipes.ui.models.RecipeDetailViewModel;
import com.temperoni.recipes.ui.views.DescriptionSection;
import com.temperoni.recipes.ui.views.IngredientsSection;

import javax.inject.Inject;

import static com.temperoni.recipes.ui.activities.RecipesListActivity.EXTRA_RECIPE_ID;
import static com.temperoni.recipes.ui.activities.RecipesListActivity.EXTRA_RECIPE_IMAGE;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailView {

    @Inject
    RecipeDetailPresenter presenter;

    private AppBarLayout appBarLayout;
    private ImageView recipeImage;
    private DescriptionSection descriptionSection;

    private IngredientsSection ingredientsSection;
    private String recipeId;

    private RecipeDetailViewModel viewmodel;

    private float appBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ((RecipesComponentProvider) getApplicationContext()).getRecipesComponent().inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        appBarHeight = getResources().getDimension(R.dimen.app_bar_height);

        recipeImage = (ImageView) findViewById(R.id.header_recipe_image);

        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //  Vertical offset == 0 indicates appBar is fully  expanded.
                setRecipeTitle(Math.abs(verticalOffset) > 350);
            }
        });

        descriptionSection = (DescriptionSection) findViewById(R.id.description_section);
        ingredientsSection = (IngredientsSection) findViewById(R.id.ingredients_section);

        recipeId = getIntent().getExtras().getString(EXTRA_RECIPE_ID);
        String imageUrl = getIntent().getExtras().getString(EXTRA_RECIPE_IMAGE);
        Picasso.with(recipeImage.getContext())
                .load(imageUrl)
                .into(recipeImage);

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

    private void setRecipeTitle(boolean appBarExpanded){
        getSupportActionBar().setTitle(appBarExpanded && viewmodel != null ? viewmodel.getName() : "");
    }

    @Override
    public void displayRecipeDetail(RecipeDetailViewModel viewModel) {
        this.viewmodel = viewModel;
        descriptionSection.setData(viewModel);
        ingredientsSection.setIngredients(viewModel.getIngredients());
    }
}
