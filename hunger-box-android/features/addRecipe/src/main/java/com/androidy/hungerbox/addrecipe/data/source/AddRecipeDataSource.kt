package com.androidy.hungerbox.addrecipe.data.source

import com.androidy.hungerbox.core.data.Result
import com.androidy.hungerbox.addrecipe.data.RecipeModel


/**
 * Created by Ankit
 */


interface AddRecipeDataSource {

    suspend fun uploadRecipe(recipeData: RecipeModel): Result<Unit>
}