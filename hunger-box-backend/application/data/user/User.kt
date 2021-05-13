package com.androidy.hungerbox.data.user

import com.androidy.hungerbox.models.user.CreateUserRequest
import kotlinx.serialization.Serializable


@Serializable
data class User(
    var uid: String,
    var userName: String? = null,
    var name: String? = null,
    val email: String? = null,
    var profileImage: String? = null,
    var createdAt: Long = System.currentTimeMillis()
){
    companion object{
        fun toUser(uid: String, user: CreateUserRequest): User{
           return User(uid = uid, email = user.email)
        }
    }
}

