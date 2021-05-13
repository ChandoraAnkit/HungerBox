package com.androidy.hungerbox.modules.auth

import com.androidy.hungerbox.data.auth.UserPrincipal
import kotlinx.coroutines.runBlocking

object AuthConfig {

    fun FirebaseAuthenticationProvider.Configuration.configure() {
        principal = { uid ->
            runBlocking { UserPrincipal(uid) }
        }
    }
}