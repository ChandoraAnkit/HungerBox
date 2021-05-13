package com.androidy.hungerbox.data.loginRegister.source

import com.androidy.hungerbox.data.Result
import com.androidy.hungerbox.data.loginRegister.User


/**
 * Created by Ankit
 */


interface LoginRegisterDataSource {

    suspend fun registerUser(email: String, password: String): Result<User>

    suspend fun loginUser(email: String, password: String): Result<Unit>

}