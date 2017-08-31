package com.temperoni.recipes.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.temperoni.recipes.R;
import com.temperoni.recipes.ui.models.InstructionItemViewModel;

import java.util.List;

/**
 * @author Leandro Temperoni
 */
public class InstructionsSection extends CardView {

    private LinearLayout instructionsItemsContainer;

    public InstructionsSection(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.instructions_section, this);
        this.instructionsItemsContainer = findViewById(R.id.instructions_container);
    }

    public void setSectionWithSteps(List<InstructionItemViewModel> instructionItemViewModels) {
        for (InstructionItemViewModel instruction : instructionItemViewModels) {
            addInstructionEntry(instruction);
        }
    }

    private void addInstructionEntry(InstructionItemViewModel viewModel) {
        InstructionEntry instructionEntry = new InstructionEntry(getContext());
        instructionEntry.setData(viewModel);
        instructionsItemsContainer.addView(instructionEntry);
    }
}
