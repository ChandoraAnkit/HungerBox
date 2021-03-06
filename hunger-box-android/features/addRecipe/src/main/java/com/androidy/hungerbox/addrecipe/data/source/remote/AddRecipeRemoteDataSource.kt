package com.androidy.hungerbox.addrecipe.data.source.remote

import com.androidy.hungerbox.core.data.Result
import com.androidy.hungerbox.addrecipe.data.RecipeModel
import com.androidy.hungerbox.addrecipe.data.source.AddRecipeDataSource
import com.androidy.hungerbox.core.utils.Constants
import com.google.firebase.FirebaseException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


/**
 * Created by Ankit
 */


class AddRecipeRemoteDataSource @Inject constructor(
        private val fireStore: FirebaseFirestore
): AddRecipeDataSource {

    override suspend fun uploadRecipe(recipeData: RecipeModel): Result<Unit> {
        return try {
            fireStore.collection(Constants.RECIPES)
                    .document(recipeData.userId!!)
                    .collection(Constants.USER_RECIPES)
                    .add(recipeData).await()
            Result.Success(Unit)
        }catch (ex: FirebaseException){
            Result.Error(ex) as Result<Unit>
        }
    }
}