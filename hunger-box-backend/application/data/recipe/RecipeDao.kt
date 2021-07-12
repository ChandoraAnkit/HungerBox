package com.androidy.hungerbox.data.recipe

import com.androidy.hungerbox.models.recipe.RecipeRequest
import com.androidy.hungerbox.models.recipe.UpdateRecipeRequest
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.*


class RecipeDao(private val database: MongoDatabase) {

    private val recipeCollection by lazy { database.getCollection<Recipe>(COLLECTION_RECIPES) }

    fun createRecipe(uid: String, recipeRequest: RecipeRequest): Recipe? {
        val recipe = Recipe.toRecipe(uid, recipeRequest)
        val isCreated = recipeCollection.insertOne(recipe).wasAcknowledged()
        if (isCreated)
            return recipe
        return null
    }

    fun updateRecipe(recipeId: String, updatedRecipe: UpdateRecipeRequest): Recipe? {
        recipeCollection.updateOne(Recipe::recipeId eq recipeId, updatedRecipe)
        return getRecipeById(recipeId)
    }

    fun deleteR ecipe(recipeId: String): Boolean {
        return recipeCollection.deleteOne(Recipe::recipeId eq recipeId)
            .wasAcknowledged()
    }

    fun getRecipeById(recipeId: String): Recipe? {
        return recipeCollection.findOne(Recipe::recipeId eq recipeId)
    }

    fun isRecipeExist(recipeId: String): Boolean {
        return getRecipeById(recipeId) != null
    }

    fun getRecipes(uid: String): List<Recipe> {
        return recipeCollection.find(Recipe::uid eq uid).toList()
    }

    companion object {
        const val COLLECTION_RECIPES = "recipes"
    }
}