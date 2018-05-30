package com.temperoni.recipes.ui.views

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import android.widget.TextView

import com.temperoni.recipes.R
import com.temperoni.recipes.ui.models.RecipeDetailViewModel

/**
 * @author Leandro Temperoni
 */
class DescriptionSection : CardView {

    private var name: TextView? = null
    private var description: TextView? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.description_section, this)
        name = findViewById(R.id.recipe_name)
        description = findViewById(R.id.recipe_description)
    }

    fun setData(viewModel: RecipeDetailViewModel) {
        name?.text = viewModel.name?.toUpperCase()
        description?.text = viewModel.introduction
    }
}
