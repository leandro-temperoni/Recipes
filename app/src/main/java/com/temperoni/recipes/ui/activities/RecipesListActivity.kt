package com.temperoni.recipes.ui.activities

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.temperoni.recipes.R
import com.temperoni.recipes.mvp.presenter.RecipesListPresenter
import com.temperoni.recipes.mvp.view.RecipesListView
import com.temperoni.recipes.ui.adapters.RecipesListAdapter
import com.temperoni.recipes.ui.models.RecipeViewModel
import com.temperoni.recipes.ui.views.VerticalSpaceItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class RecipesListActivity : BaseActivity(), RecipesListView {

    @Inject
    lateinit var presenter: RecipesListPresenter

    private val mAdapter: RecipesListAdapter = RecipesListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component.inject(this)

        val mLayoutManager = LinearLayoutManager(this)
        recipes.layoutManager = mLayoutManager
        recipes.addItemDecoration(VerticalSpaceItemDecoration(30))
        recipes.itemAnimator = DefaultItemAnimator()
        recipes.setHasFixedSize(true)
        mAdapter.listener = presenter
        recipes.adapter = mAdapter

        presenter.view = this
        presenter.fetchData()
    }

    public override fun onResume() {
        super.onResume()
        presenter.register()
    }

    public override fun onPause() {
        super.onPause()
        presenter.unregister()
    }

    override fun displayLoading() {
        error.visibility = GONE
        empty.visibility = GONE
        recipes.visibility = GONE
        progress.visibility = VISIBLE
    }

    override fun displayError() {
        error.visibility = VISIBLE
        empty.visibility = GONE
        recipes.visibility = GONE
        progress.visibility = GONE
    }

    override fun displayEmptyState() {
        error.visibility = GONE
        empty.visibility = VISIBLE
        recipes.visibility = GONE
        progress.visibility = GONE
    }

    override fun displayRecipes(data: List<RecipeViewModel>) {
        mAdapter.recipes = data
        error.visibility = GONE
        empty.visibility = GONE
        recipes.visibility = VISIBLE
        progress.visibility = GONE
    }

    override fun navigateToRecipeDetail(recipeId: String, imageUrl: String, sharedView: View) {
        val intent = Intent(this, RecipeDetailActivity::class.java)
        intent.putExtra(EXTRA_RECIPE_ID, recipeId)
        intent.putExtra(EXTRA_RECIPE_IMAGE, imageUrl)

        val options = ActivityOptions.makeSceneTransitionAnimation(this, sharedView, "recipe_image")

        startActivity(intent, options.toBundle())
    }

    companion object {

        const val EXTRA_RECIPE_ID = "EXTRA_RECIPE_ID"
        const val EXTRA_RECIPE_IMAGE = "EXTRA_RECIPE_IMAGE"
    }
}
