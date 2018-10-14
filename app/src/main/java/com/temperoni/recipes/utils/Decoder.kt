package com.temperoni.recipes.utils

import com.google.gson.Gson
import com.temperoni.recipes.domain.dto.Recipe
import org.apache.commons.io.IOUtils

import java.io.IOException

/**
 * @author Leandro Temperoni
 */
class Decoder {

    fun decodeJson(fileName: String): List<Recipe>? {
        return Gson().fromJson(getJson(fileName), RecipeList::class.java).recipes
    }

    private fun getJson(fileName: String): String {
        var result = ""

        val classLoader = javaClass.classLoader
        try {
            result = IOUtils.toString(classLoader?.getResourceAsStream(fileName)?.reader())
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    private inner class RecipeList {
        val recipes: List<Recipe>? = null
    }
}
