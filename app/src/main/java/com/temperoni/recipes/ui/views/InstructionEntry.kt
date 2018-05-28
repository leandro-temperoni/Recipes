package com.temperoni.recipes.ui.views

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.temperoni.recipes.R
import com.temperoni.recipes.ui.models.InstructionItemViewModel

/**
 * @author Leandro Temperoni
 */
class InstructionEntry(context: Context) : LinearLayout(context) {

    private val instructionsTitle: TextView
    private val instructionsItemsContainer: LinearLayout

    init {
        View.inflate(getContext(), R.layout.instruction_item, this)
        this.instructionsItemsContainer = findViewById(R.id.instructions_item_container)
        this.instructionsTitle = findViewById(R.id.instruction_title)
    }

    fun setData(viewModel: InstructionItemViewModel) {
        instructionsTitle.visibility = if (viewModel.hasDescriptionAvailable()) View.VISIBLE else View.GONE
        instructionsTitle.text = viewModel.description
        for (step in viewModel.steps) {
            addStepEntry(step)
        }
    }

    private fun addStepEntry(step: String) {
        val stepEntry = StepEntry(context)
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(0, 16, 0, 0)
        stepEntry.text = step
        stepEntry.layoutParams = params
        instructionsItemsContainer.addView(stepEntry)
    }
}
