package com.androidy.hungerbox.statusPages

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*

fun StatusPages.Configuration.userStatusPages() {
    exception<UserNotFoundException> { cause ->
        call.respondText(cause.message, status = HttpStatusCode.NotFound)
    }

    exception<InvalidUserException> { cause ->
        call.respondText(cause.message, status = HttpStatusCode.BadRequest)
    }
}

data class UserNotFoundException(override val message: String = "User not found") : Exception()

data class InvalidUserException(override val message: String = "User is already taken") : Exception()

