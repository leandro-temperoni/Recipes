package com.temperoni.recipes.ui.views

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

import com.temperoni.recipes.R
import com.temperoni.recipes.ui.models.IngredientEntryViewModel

/**
 * @author Leandro Temperoni
 */
class IngredientsSection : CardView {

    private var ingredientsContainer: LinearLayout? = null

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
        View.inflate(context, R.layout.ingredients_section, this)
        this.ingredientsContainer = findViewById(R.id.ingredients_container)
    }

    fun setIngredients(ingredients: List<IngredientEntryViewModel>?) {
        ingredientsContainer?.removeAllViews()
        ingredients?.forEach {
            addIngredientEntry(it)
        }
    }

    private fun addIngredientEntry(viewModel: IngredientEntryViewModel) {
        val ingredientEntry = IngredientEntry(context)
        ingredientEntry.setData(viewModel)
        ingredientsContainer?.addView(ingredientEntry)
    }
}
