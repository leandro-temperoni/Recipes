package com.temperoni.recipes.ui.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.temperoni.recipes.R;
import com.temperoni.recipes.ui.models.RecipeDetailViewModel;

/**
 * @author Leandro Temperoni
 */
public class DescriptionSection extends CardView {

    private TextView name;
    private TextView description;

    public DescriptionSection(Context context) {
        super(context);
        init();
    }

    public DescriptionSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DescriptionSection(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.description_section, this);
        this.name = findViewById(R.id.recipe_name);
        this.description = findViewById(R.id.recipe_description);
    }

    public void setData(RecipeDetailViewModel viewModel) {
        this.name.setText(viewModel.getName().toUpperCase());
        this.description.setText(viewModel.getIntroduction());
    }
}
