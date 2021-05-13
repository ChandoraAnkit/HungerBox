package com.androidy.hungerbox.data.addRecipe.source

import com.androidy.hungerbox.data.Result
import com.androidy.hungerbox.data.addRecipe.RecipeModel


/**
 * Created by Ankit
 */


interface AddRecipeDataSource {

    suspend fun uploadRecipe(recipeData: RecipeModel): Result<Unit>
}