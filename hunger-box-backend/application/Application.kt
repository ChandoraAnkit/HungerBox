package com.androidy.hungerbox

import com.androidy.hungerbox.data.HungerBoxDb
import com.androidy.hungerbox.data.auth.UserPrincipal
import com.androidy.hungerbox.modules.auth.AuthConfig.configure

import com.androidy.hungerbox.modules.auth.FirebaseAdmin
import com.androidy.hungerbox.modules.auth.firebaseAuthModule
import com.androidy.hungerbox.modules.recipe.recipeModule
import com.androidy.hungerbox.modules.statusPagesModule
import com.androidy.hungerbox.modules.user.userModule
import com.androidy.hungerbox.utils.Constants
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

import io.ktor.serialization.*
import kotlinx.serialization.json.Json
import io.ktor.features.CallLogging
import org.litote.kmongo.util.UpdateConfiguration


fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

val ApplicationCall.user get() = authentication.principal<UserPrincipal>()!!

@Suppress("unused")
fun Application.module() {

    install(CallLogging) {
        level = org.slf4j.event.Level.DEBUG
    }

    FirebaseAdmin.init()
    HungerBoxDb.init(Constants.DB_NAME)
    UpdateConfiguration.updateOnlyNotNullProperties = true

    install(ContentNegotiation) {
        json(json = Json {
            coerceInputValues = true
            ignoreUnknownKeys = true
            prettyPrint = true
        })
    }
    install(Authentication) { firebaseAuthModule { configure() } }
    install(StatusPages) {
        statusPagesModule()
    }

    install(Routing) {
        userModule()
        recipeModule()
    }
}