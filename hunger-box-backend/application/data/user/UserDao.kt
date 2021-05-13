package com.androidy.hungerbox.data.user

import com.androidy.hungerbox.models.user.CreateUserRequest
import com.androidy.hungerbox.models.user.UpdateUserProfileRequest
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.*


class UserDao(private val database: MongoDatabase) {

    fun createUser(uid: String, user: CreateUserRequest): String {
        val isCreated =
            database.getCollection<User>(COLLECTION_USERS).insertOne(User.toUser(uid, user)).wasAcknowledged()
        if (isCreated)
            return uid
        return ""
    }

    fun getUserProfileByUId(uid: String): User? {
        return database.getCollection<User>(COLLECTION_USERS).findOne(User::uid eq uid)
    }

    fun updateUserProfileByUid(uid: String, updateProfile: UpdateUserProfileRequest): User? {
        database.getCollection<User>(COLLECTION_USERS).updateOne(User::uid eq uid, updateProfile)
        return getUserProfileByUId(uid)
    }

    fun isUserExist(uid: String): Boolean {
        return getUserProfileByUId(uid) != null
    }

    companion object {
        const val COLLECTION_USERS = "users"
    }
}


