package com.temperoni.recipes.ui.views

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

import com.temperoni.recipes.R
import com.temperoni.recipes.ui.models.InstructionItemViewModel

/**
 * @author Leandro Temperoni
 */
class InstructionsSection(context: Context, attrs: AttributeSet?) : CardView(context, attrs) {

    private val instructionsItemsContainer: LinearLayout

    init {
        View.inflate(getContext(), R.layout.instructions_section, this)
        this.instructionsItemsContainer = findViewById(R.id.instructions_container)
    }

    fun setSectionWithSteps(instructionItemViewModels: List<InstructionItemViewModel>) {
        instructionsItemsContainer.removeAllViews()
        for (instruction in instructionItemViewModels) {
            addInstructionEntry(instruction)
        }
    }

    private fun addInstructionEntry(viewModel: InstructionItemViewModel) {
        val instructionEntry = InstructionEntry(context)
        instructionEntry.setData(viewModel)
        instructionsItemsContainer.addView(instructionEntry)
    }
}
