package com.temperoni.recipes.ui.views;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.temperoni.recipes.R;
import com.temperoni.recipes.ui.models.InstructionItemViewModel;

import static android.widget.LinearLayout.LayoutParams.MATCH_PARENT;
import static android.widget.LinearLayout.LayoutParams.WRAP_CONTENT;

/**
 * @author Leandro Temperoni
 */
public class InstructionEntry extends LinearLayout {

    private TextView instructionsTitle;
    private LinearLayout instructionsItemsContainer;

    public InstructionEntry(Context context) {
        super(context);
        inflate(getContext(), R.layout.instruction_item, this);
        this.instructionsItemsContainer = findViewById(R.id.instructions_item_container);
        this.instructionsTitle = findViewById(R.id.instruction_title);
    }

    public void setData(InstructionItemViewModel viewModel) {
        instructionsTitle.setVisibility(viewModel.hasDescriptionAvailable() ? VISIBLE : GONE);
        instructionsTitle.setText(viewModel.getDescription());
        for (String step : viewModel.getSteps()) {
            addStepEntry(step);
        }
    }

    private void addStepEntry(String step) {
        StepEntry stepEntry = new StepEntry(getContext());
        LayoutParams params = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.setMargins(0, 16, 0 ,0);
        stepEntry.setText(step);
        stepEntry.setLayoutParams(params);
        instructionsItemsContainer.addView(stepEntry);
    }
}
