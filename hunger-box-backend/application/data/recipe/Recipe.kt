package com.androidy.hungerbox.data.recipe

import com.androidy.hungerbox.models.recipe.Direction
import com.androidy.hungerbox.models.recipe.Ingredient
import com.androidy.hungerbox.models.recipe.RecipeRequest
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class Recipe(
    val uid: String,
    var recipeId: String = ObjectId.get().toString(),
    var recipeName: String? = null,
    var recipeDescription: String? = null,
    var cookingTime: String? = null,
    var prepareTime: String? = null,
    var servingCount: Int? = 0,
    var directions: List<Direction>? = emptyList(),
    var ingredients: List<Ingredient>? = emptyList(),
    var createdAt: Long = System.currentTimeMillis(),
    var updatedAt: Long = System.currentTimeMillis()
) {
    companion object {
        fun toRecipe(uid: String, recipeRequest: RecipeRequest): Recipe {
            with(recipeRequest) {
                return Recipe(
                    uid = uid,
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


