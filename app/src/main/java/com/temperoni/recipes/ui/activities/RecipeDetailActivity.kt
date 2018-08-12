package com.temperoni.recipes.ui.activities

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import com.squareup.picasso.Picasso
import com.temperoni.recipes.R
import com.temperoni.recipes.mvp.presenter.RecipeDetailPresenter
import com.temperoni.recipes.mvp.view.RecipeDetailView
import com.temperoni.recipes.ui.activities.RecipesListActivity.Companion.EXTRA_RECIPE_ID
import com.temperoni.recipes.ui.activities.RecipesListActivity.Companion.EXTRA_RECIPE_IMAGE
import com.temperoni.recipes.ui.models.RecipeDetailViewModel
import kotlinx.android.synthetic.main.activity_recipe_detail.*
import kotlinx.android.synthetic.main.content_recipe_detail.*
import javax.inject.Inject

class RecipeDetailActivity : BaseActivity(), RecipeDetailView {

    @Inject
    lateinit var presenter: RecipeDetailPresenter

    private var viewModel: RecipeDetailViewModel? = null

    private var recipeId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        component.inject(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val listener : AppBarLayout.OnOffsetChangedListener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            //  Vertical offset == 0 indicates appBar is fully  expanded.
            setRecipeTitle(Math.abs(verticalOffset) > 350)
        }
        appbar.addOnOffsetChangedListener(listener)

        recipeId = intent?.extras?.getString(EXTRA_RECIPE_ID)
        val imageUrl = intent?.extras?.getString(EXTRA_RECIPE_IMAGE)
        Picasso.with(headerRecipeImage.context)
                .load(imageUrl)
                .into(headerRecipeImage)

        presenter.view = this
    }

    public override fun onResume() {
        super.onResume()
        presenter.register()
        presenter.fetchRecipeDetail(recipeId)
    }

    public override fun onPause() {
        super.onPause()
        presenter.unregister()
    }

    private fun setRecipeTitle(appBarExpanded: Boolean) {
        supportActionBar?.title = when {
            appBarExpanded -> viewModel?.name ?: ""
            else -> ""
        }
    }

    override fun displayRecipeDetail(viewModel: RecipeDetailViewModel?) {
        this.viewModel = viewModel
        viewModel?.let {
            description.setData(it)
            ingredients.setIngredients(it.ingredients)
            instructions.setSectionWithSteps(it.instructions)
        }
    }
}
