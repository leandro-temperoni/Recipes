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

public class IngredientsSection extends CardView {

    private LinearLayout ingredientsContainer;

    public IngredientsSection(Context context) {
        super(context);
        init();
    }

    public IngredientsSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IngredientsSection(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.ingredients_section, this);
        this.ingredientsContainer = findViewById(R.id.ingredients_container);
    }

    public void setIngredients(List<IngredientEntryViewModel> ingredients) {
        for (IngredientEntryViewModel ingredient : ingredients) {
            addIngredientEntry(ingredient);
        }
    }

    private void addIngredientEntry(IngredientEntryViewModel viewModel) {
        IngredientEntry ingredientEntry = new IngredientEntry(getContext());
        ingredientEntry.setData(viewModel);
        ingredientsContainer.addView(ingredientEntry);
    }
}
