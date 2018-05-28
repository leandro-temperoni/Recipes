package com.temperoni.recipes.ui.views

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.view.View

import com.temperoni.recipes.R

/**
 * @author Leandro Temperoni
 */
class StepEntry(context: Context) : AppCompatTextView(context) {

    init {
        View.inflate(getContext(), R.layout.step_entry, null)
    }
}
