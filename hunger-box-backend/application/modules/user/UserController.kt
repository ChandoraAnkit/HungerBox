package com.androidy.hungerbox.modules.user

import com.androidy.hungerbox.data.HungerBoxDb
import com.androidy.hungerbox.data.user.User
import com.androidy.hungerbox.data.user.UserDao
import com.androidy.hungerbox.models.user.CreateUserRequest
import com.androidy.hungerbox.models.user.UpdateUserProfileRequest
import com.androidy.hungerbox.statusPages.InvalidUserException
import com.androidy.hungerbox.statusPages.MissingArgumentException
import com.androidy.hungerbox.statusPages.ServerException
import com.androidy.hungerbox.statusPages.UserNotFoundException

class UserController {

    private val userDao by lazy { UserDao(HungerBoxDb.database) }

    fun createUser(uid: String, user: CreateUserRequest): String {

        if (userDao.getUserProfileByUId(uid) != null)
            throw InvalidUserException("User with the email ${user.email} is already added")

        val createdUserId = userDao.createUser(uid, user)
        if (createdUserId.isEmpty())
            throw ServerException()
        return "User successfully created"
    }

    fun getUserProfileByUid(uid: String): User {
        return userDao.getUserProfileByUId(uid)
            ?: throw UserNotFoundException("User with the id $uid not found.")
    }

    fun updateProfileByUid(uid: String, user: UpdateUserProfileRequest): User {

        if (!userDao.isUserExist(uid))
            throw UserNotFoundException("User with the id $uid not found.")

        return userDao.updateUserProfileByUid(uid, user)?: throw ServerException("Failed to update the profile.")

    }

}