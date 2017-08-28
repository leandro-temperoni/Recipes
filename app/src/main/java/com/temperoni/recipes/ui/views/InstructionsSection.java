package com.temperoni.recipes.ui.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.temperoni.recipes.R;
import com.temperoni.recipes.ui.models.IngredientEntryViewModel;

import java.util.List;

/**
 * @author Leandro Temperoni
 */

public class InstructionsSection extends CardView {

    private LinearLayout instructionsContainer;

    public InstructionsSection(Context context) {
        super(context);
        init();
    }

    public InstructionsSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InstructionsSection(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.instructions_section, this);
        this.instructionsContainer = findViewById(R.id.instructions_container);
    }

    public void setSectionWithSteps(List<IngredientEntryViewModel> ingredients) {

    }

    private void addIngredientEntry(IngredientEntryViewModel viewModel) {

    }
}
