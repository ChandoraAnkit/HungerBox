package com.androidy.hungerbox.modules.recipe

import com.androidy.hungerbox.models.recipe.RecipeRequest
import com.androidy.hungerbox.models.recipe.UpdateRecipeRequest
import com.androidy.hungerbox.user
import com.androidy.hungerbox.utils.FailureMessages
import com.androidy.hungerbox.utils.safeParameter
import com.androidy.hungerbox.utils.safeReceive
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.recipeModule() {

    val recipeController by lazy { RecipeController() }

    authenticate {

        route("/recipe") {

            post("/create") {
                val recipeRequest = call.safeReceive<RecipeRequest>(error = FailureMessages.RECIPE_ARGS_MISSING)
                val createdRecipe = recipeController.createRecipe(call.user.uid, recipeRequest)
                call.respond(HttpStatusCode.Created, createdRecipe)
            }

            get("/{id}"){
                val recipeId = call.safeParameter("id", error = FailureMessages.RECIPE_ID_MISSING)
                call.respond(recipeController.getRecipeById(recipeId))
            }

            put("/{id}") {
                val recipeId = call.safeParameter("id", error = FailureMessages.RECIPE_ID_MISSING)
                val updatedRecipeRequest = call.receive<UpdateRecipeRequest>()
                call.respond(recipeController.updateRecipe(recipeId, updatedRecipeRequest))
            }

            delete("/{id}") {
                val recipeId = call.safeParameter("id", error = FailureMessages.RECIPE_ID_MISSING)
                recipeController.deleteRecipe(recipeId)
                call.respond(HttpStatusCode.OK)
            }
        }

        get("/recipes") {
            val recipes = recipeController.getRecipes(call.user.uid)
            call.respond(recipes)
        }
    }
}