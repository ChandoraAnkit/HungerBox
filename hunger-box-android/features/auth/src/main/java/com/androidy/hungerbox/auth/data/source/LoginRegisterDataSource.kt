package com.androidy.hungerbox.auth.data.source

import com.androidy.hungerbox.core.data.Result
import com.androidy.hungerbox.auth.data.User


/**
 * Created by Ankit
 */


interface LoginRegisterDataSource {

    suspend fun registerUser(email: String, password: String): Result<User>

    suspend fun loginUser(email: String, password: String): Result<Unit>

}