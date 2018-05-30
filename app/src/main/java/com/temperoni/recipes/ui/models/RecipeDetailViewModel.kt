package com.temperoni.recipes.ui.models

import com.temperoni.recipes.domain.dto.Ingredient
import com.temperoni.recipes.domain.dto.InstructionItem

/**
 * @author Leandro Temperoni
 */
class RecipeDetailViewModel private constructor(builder: Builder) {

    val id: Int?
    private val imageUrl: String?
    val name: String?
    val introduction: String?
    val ingredients: List<IngredientEntryViewModel>?
    val instructions: List<InstructionItemViewModel>?

    init {
        id = builder.id
        imageUrl = builder.imageUrl
        ingredients = builder.ingredients
        introduction = builder.introduction
        name = builder.name
        instructions = builder.instructions
    }

    class Builder(var id: Int, var name: String,
                  var imageUrl: String, var introduction: String) {

        lateinit var ingredients: MutableList<IngredientEntryViewModel>
        lateinit var instructions: MutableList<InstructionItemViewModel>

        private lateinit var ingredientList: List<Ingredient?>
        private lateinit var instructionItemList: List<InstructionItem?>

        fun withIngredients(ingredientList: List<Ingredient?>): Builder {
            this.ingredientList = ingredientList
            return this
        }

        fun withInstructions(instructionItemList: List<InstructionItem?>): Builder {
            this.instructionItemList = instructionItemList
            return this
        }

        fun build(): RecipeDetailViewModel {
            getIngredients(ingredientList)
            getInstructions(instructionItemList)

            return RecipeDetailViewModel(this)
        }

        private fun getIngredients(ingredientList: List<Ingredient?>) {
            ingredients = mutableListOf()
            for (ingredient in ingredientList) {
                if (ingredient != null) {
                    ingredients.add(getIngredientViewModel(ingredient))
                }
            }

            if (!ingredients.isEmpty()) {
                ingredients[ingredientList.size - 1].isLastEntry = true
            }
        }

        private fun getIngredientViewModel(ingredient: Ingredient): IngredientEntryViewModel {
            val ingredientEntryViewModel = IngredientEntryViewModel()
            ingredientEntryViewModel.description = "${ingredient.amount} de ${ingredient.name}"
            return ingredientEntryViewModel
        }

        private fun getInstructions(instructionItemList: List<InstructionItem?>) {
            instructions = mutableListOf()
            for (instructionItem in instructionItemList) {
                if (instructionItem != null) {
                    instructions.add(getInstructionItemViewModel(instructionItem, instructionItemList.size > 1))
                }
            }
        }

        private fun getInstructionItemViewModel(instructionItem: InstructionItem,
                                                shouldShowTitle: Boolean): InstructionItemViewModel {
            val instructionItemViewModel = InstructionItemViewModel()
            instructionItemViewModel.description = instructionItem.description
            instructionItemViewModel.setDescriptionAvailable(shouldShowTitle)
            instructionItemViewModel.steps = getStepsWithFullDescription(instructionItem.steps)
            return instructionItemViewModel
        }

        private fun getStepsWithFullDescription(steps: List<String?>?): MutableList<String> {
            val fullSteps = mutableListOf<String>()
            steps?.forEachIndexed { index, step ->
                fullSteps.add( "$index) $step")
            }
            return fullSteps
        }
    }
}
