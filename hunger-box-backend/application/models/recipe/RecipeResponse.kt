package com.androidy.hungerbox.models.recipe

import com.androidy.hungerbox.data.recipe.Recipe
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponse(
    val recipeId: String,
    val recipeName: String? = null,
    val recipeDescription: String? = null,
    var cookingTime: String? = null,
    var prepareTime: String? = null,
    var servingCount: Int? = 0,
    var directions: List<Direction>? = null,
    var ingredients: List<Ingredient>? = null,
) {
    companion object {

        fun toRecipeSimplifiedResponse(recipe: Recipe): RecipeResponse {
            with(recipe) {
                return RecipeResponse(
                    recipeId = recipeId, recipeName = recipeName,
                    recipeDescription = recipeDescription
                )
            }
        }

        fun toRecipeDetailedResponse(recipe: Recipe): RecipeResponse {
            with(recipe) {
                return RecipeResponse(
                    recipeId = recipeId,
                    recipeName = recipeName,
                    recipeDescription = recipeDescription,
                    cookingTime = cookingTime,
                    prepareTime = prepareTime,
                    servingCount = servingCount,
                    directions = directions,
                    ingredients = ingredients
                )
            }
        }
    }
}