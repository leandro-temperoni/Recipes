package com.temperoni.recipes.ui.activities

import android.support.v7.app.AppCompatActivity
import com.temperoni.recipes.component.RecipesComponentProvider

abstract class BaseActivity: AppCompatActivity() {

    open val component by lazy { (applicationContext as RecipesComponentProvider).recipesComponent }
}