package com.temperoni.recipes.domain.dto

/**
 * @author Leandro Temperoni
 */
data class InstructionItem(var description: String? = "",
                           var steps: List<String?>? = null)
