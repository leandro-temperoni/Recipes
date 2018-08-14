package com.temperoni.recipes.ui.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import com.temperoni.recipes.R

/**
 * @author Leandro Temperoni
 */
@Suppress("LeakingThis")
abstract class InfoView(context: Context?, attrs: AttributeSet?)
    : ConstraintLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.info_view, this)
    }
}