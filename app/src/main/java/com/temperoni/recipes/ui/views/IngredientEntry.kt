package com.temperoni.recipes.ui.views

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import com.temperoni.recipes.R
import com.temperoni.recipes.ui.models.IngredientEntryViewModel

/**
 * @author Leandro Temperoni
 */
class IngredientEntry(context: Context) : LinearLayout(context) {

    private var description: TextView? = null
    private var divider: View? = null

    init {
        View.inflate(context, R.layout.ingredient_item, this)
        this.description = findViewById(R.id.ingredient_description)
        this.divider = findViewById(R.id.divider)
    }

    fun setData(viewModel: IngredientEntryViewModel) {
        description!!.text = viewModel.description
        if (viewModel.isLastEntry) {
            divider!!.visibility = View.GONE
        }
    }
}
