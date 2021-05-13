package com.androidy.hungerbox.statusPages

import com.androidy.hungerbox.utils.FailureMessages
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*

fun StatusPages.Configuration.generalStatusPages() {
    exception<MissingArgumentException> { cause ->
        call.respondText(cause.message, status = HttpStatusCode.BadRequest)
    }

    exception<ServerException> { cause ->
        call.respondText(cause.message, status = HttpStatusCode.InternalServerError)
    }
}

data class MissingArgumentException(override val message: String = "Missing argument") : Exception()

data class ServerException(override val message: String = FailureMessages.SOMETHING_WENT_WRONG) : Exception()


