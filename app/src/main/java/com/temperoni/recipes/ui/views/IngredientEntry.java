package com.temperoni.recipes.ui.views;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.temperoni.recipes.R;
import com.temperoni.recipes.ui.models.IngredientEntryViewModel;

/**
 * @author Leandro Temperoni
 */
public class IngredientEntry extends LinearLayout {

    private TextView description;
    private View divider;

    public IngredientEntry(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.ingredient_item, this);
        this.description = findViewById(R.id.ingredient_description);
        this.divider = findViewById(R.id.divider);
    }

    public void setData(IngredientEntryViewModel viewModel) {
        description.setText(viewModel.getDescription());
        if (viewModel.isLastEntry()) {
            divider.setVisibility(GONE);
        }
    }
}
