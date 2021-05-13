package com.androidy.hungerbox.models.recipe

import com.androidy.hungerbox.base.BaseRequest
import com.androidy.hungerbox.utils.FailureMessages
import kotlinx.serialization.Serializable

@Serializable
data class RecipeRequest(
    val recipeName: String,
    val recipeDescription: String,
    val cookingTime: String,
    val prepareTime: String,
    val servingCount: Int,
    val directions: List<Direction>,
    val ingredients: List<Ingredient>
) : BaseRequest {

    override fun validate(): Pair<Boolean, String> {
        if (directions.isEmpty())
            return Pair(false, "Minimum one direction is mandatory")

        if (ingredients.isEmpty())
            return Pair(false, "Minimum one ingredient is mandatory")

        return Pair(true, "")
    }
}

@Serializable
data class UpdateRecipeRequest(
    val recipeName: String? = null,
    val recipeDescription: String? = null,
    val cookingTime: String? = null,
    val prepareTime: String? = null,
    val servingCount: Int = 0,
    val directions: List<Direction>? = emptyList(),
    val ingredients: List<Ingredient>? = emptyList()
) : BaseRequest {

    override fun validate(): Pair<Boolean, String> {
        if (recipeName == null &&
            recipeDescription == null && cookingTime == null &&
            prepareTime == null && directions.isNullOrEmpty() && ingredients.isNullOrEmpty()
        )
            return Pair(false, FailureMessages.ARGUMENT_MISSING)

        return Pair(true, "")

    }
}

@Serializable
data class Ingredient(val name: String)

@Serializable
data class Direction(val direction: String)