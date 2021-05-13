package com.androidy.hungerbox.statusPages

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*

fun StatusPages.Configuration.recipeStatusPages() {
    exception<RecipeNotFoundException> { cause ->
        call.respondText(cause.message, status = HttpStatusCode.BadRequest)
    }
}

data class RecipeNotFoundException(override val message: String) : Exception()