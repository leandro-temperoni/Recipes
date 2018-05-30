package com.temperoni.recipes.ui.models

/**
 * @author Leandro Temperoni
 */
class InstructionItemViewModel {

    var description: String? = null
    private var descriptionAvailable: Boolean = false
    var steps: List<String>? = null

    fun hasDescriptionAvailable(): Boolean {
        return descriptionAvailable
    }

    fun setDescriptionAvailable(descriptionAvailable: Boolean) {
        this.descriptionAvailable = descriptionAvailable
    }
}
