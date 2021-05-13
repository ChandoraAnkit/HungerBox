package com.androidy.hungerbox.modules.auth

import com.androidy.hungerbox.statusPages.UserNotFoundException
import com.androidy.hungerbox.utils.FailureMessages
import com.google.firebase.ErrorCode
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*


fun Authentication.Configuration.firebaseAuthModule(
    name: String? = null,
    configure: FirebaseAuthenticationProvider.Configuration.() -> Unit
) {
    val provider = FirebaseAuthenticationProvider.Configuration(name).apply(configure).build()
    provider.pipeline.intercept(AuthenticationPipeline.RequestAuthentication) { context ->
        try {

            val email = provider.email(call)
            val uid = if (email.isNullOrEmpty()) {
                val token = provider.token(call) ?: throw FirebaseAuthException(
                    FirebaseException(
                        ErrorCode.UNAUTHENTICATED,
                        "No token could be found",
                        null
                    )
                )
                FirebaseAuth.getInstance().verifyIdToken(token).uid
            } else {
                FirebaseAuth.getInstance().getUserByEmail(email).uid
            }
            provider.principle?.let { it.invoke(uid)?.let { principle -> context.principal(principle) } }


        } catch (e: Exception) {
            val message = if (e is FirebaseAuthException)
                "Authentication failed: ${e.message ?: FailureMessages.SOMETHING_WENT_WRONG }"
            else
                e.message ?: FailureMessages.SOMETHING_WENT_WRONG

            call.respond(HttpStatusCode.Unauthorized, message)
            context.challenge.complete()
            finish()
        }
    }
    register(provider)
}


