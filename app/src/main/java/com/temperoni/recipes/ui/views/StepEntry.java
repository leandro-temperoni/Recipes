package com.temperoni.recipes.ui.views;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;

import com.temperoni.recipes.R;

/**
 * @author Leandro Temperoni
 */
public class StepEntry extends AppCompatTextView {

    public StepEntry(Context context) {
        super(context);
        inflate(getContext(), R.layout.step_entry, null);
    }
}
