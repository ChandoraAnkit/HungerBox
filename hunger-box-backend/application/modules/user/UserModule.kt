package com.androidy.hungerbox.modules.user

import com.androidy.hungerbox.models.user.CreateUserRequest
import com.androidy.hungerbox.models.user.UpdateUserProfileRequest
import com.androidy.hungerbox.user
import com.androidy.hungerbox.utils.safeReceive
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.userModule() {

    val userController: UserController by lazy { UserController() }

    authenticate {
        route("/user") {

            post("/add") {
                val user = call.safeReceive<CreateUserRequest>()
                val id = userController.createUser(call.user.uid, user)
                call.respond(HttpStatusCode.Created, id)
            }

            route("/profile") {
                put {
                    val updateProfile = call.safeReceive<UpdateUserProfileRequest>()
                    call.respond(userController.updateProfileByUid(call.user.uid, updateProfile))
                }
                get {
                    call.respond(userController.getUserProfileByUid(call.user.uid))
                }
            }
        }
    }
}

