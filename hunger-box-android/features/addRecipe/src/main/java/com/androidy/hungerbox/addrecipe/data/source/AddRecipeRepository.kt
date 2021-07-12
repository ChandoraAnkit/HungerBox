package com.androidy.hungerbox.addrecipe.data.source

import com.androidy.hungerbox.addrecipe.data.RecipeModel
import com.androidy.hungerbox.addrecipe.data.source.remote.AddRecipeRemoteDataSource
import javax.inject.Inject


/**
 * Created by Ankit
 */


class AddRecipeRepository @Inject constructor(
        private val remoteDataSource: AddRecipeRemoteDataSource
)  : AddRecipeDataSource {

    override suspend fun uploadRecipe(recipeData: RecipeModel) =
            remoteDataSource.uploadRecipe(recipeData)
}