package com.androidy.hungerbox.modules.recipe

import com.androidy.hungerbox.data.HungerBoxDb
import com.androidy.hungerbox.data.recipe.Recipe
import com.androidy.hungerbox.data.recipe.RecipeDao
import com.androidy.hungerbox.models.recipe.RecipeRequest
import com.androidy.hungerbox.models.recipe.RecipeResponse
import com.androidy.hungerbox.models.recipe.UpdateRecipeRequest
import com.androidy.hungerbox.statusPages.MissingArgumentException
import com.androidy.hungerbox.statusPages.RecipeNotFoundException
import com.androidy.hungerbox.statusPages.ServerException

class RecipeController {

    private val recipeDao by lazy { RecipeDao(HungerBoxDb.database) }

    fun createRecipe(uid: String, recipeRequest: RecipeRequest): RecipeResponse {
        recipeDao.createRecipe(uid, recipeRequest)?.let {
            return RecipeResponse.toRecipeDetailedResponse(it)
        } ?: throw ServerException("Failed to create recipe")
    }

    fun updateRecipe(recipeId: String, updateRecipeRequest: UpdateRecipeRequest): Recipe {

        return recipeDao.updateRecipe(recipeId, updateRecipeRequest)
            ?: throw RecipeNotFoundException("Recipe with the ID $recipeId not found")
    }

    fun deleteRecipe(recipeId: String): Boolean {

        if (!recipeDao.isRecipeExist(recipeId))
            throw RecipeNotFoundException("Recipe with the ID $recipeId not found")

        if (!recipeDao.deleteRecipe(recipeId))
            throw ServerException("Failed to delete the recipe")

        return true
    }

    fun getRecipeById(recipeId: String): RecipeResponse {
        return recipeDao.getRecipeById(recipeId)?.let {
            RecipeResponse.toRecipeDetailedResponse(it)
        } ?: throw RecipeNotFoundException("Recipe with the ID $recipeId not found")
    }

    fun getRecipes(uid: String): List<RecipeResponse> {
        return recipeDao.getRecipes(uid).map {
            RecipeResponse.toRecipeSimplifiedResponse(it)
        }
    }
}