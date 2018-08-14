package com.temperoni.recipes.ui.views

import android.content.Context
import android.util.AttributeSet
import com.temperoni.recipes.R
import kotlinx.android.synthetic.main.info_view.view.*

/**
 * @author Leandro Temperoni
 */
class ErrorView(context: Context?, attrs: AttributeSet?)
    : InfoView(context, attrs) {

    init {
        error_image.setImageResource(R.drawable.ic_error_outline_black)
        error_text.setText(R.string.error_view_text)
    }
}