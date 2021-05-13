package com.androidy.hungerbox.modules.auth

import com.androidy.hungerbox.utils.Constants
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*

class FirebaseAuthenticationProvider (config: Configuration) : AuthenticationProvider(config) {

    val token: (ApplicationCall) -> String? = config.token
    val principle: ((uid: String) -> Principal?)? = config.principal
    val email: (ApplicationCall) -> String? = config.email

    class Configuration (name: String?) : AuthenticationProvider.Configuration(name) {

        var token: (ApplicationCall) -> String? = { call -> call.request.parseAuthorizationToken() }

        var email: (ApplicationCall) -> String? = { call -> call.request.header(Constants.HEADER_EMAIL) }

        var principal: ((uid: String) -> Principal?)? = null

        fun build() = FirebaseAuthenticationProvider(this)

    }
}

fun ApplicationRequest.parseAuthorizationToken(): String? = authorization()?.let {
    it.split(" ")[1]
}
