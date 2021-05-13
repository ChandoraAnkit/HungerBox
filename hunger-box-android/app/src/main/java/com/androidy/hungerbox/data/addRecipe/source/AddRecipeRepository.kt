package com.androidy.hungerbox.data.addRecipe.source

import com.androidy.hungerbox.data.addRecipe.RecipeModel
import com.androidy.hungerbox.data.addRecipe.source.remote.AddRecipeRemoteDataSource
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