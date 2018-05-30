package com.temperoni.recipes.domain.event

import com.temperoni.recipes.domain.dto.Recipe

/**
 * @author Leandro Temperoni
 */
class RecipesEvent : ResponseEvent<List<Recipe?>?>()
