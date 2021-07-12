package com.androidy.hungerbox.auth.data.source

import com.androidy.hungerbox.auth.data.source.remote.LoginRegisterRemoteDataSource
import javax.inject.Inject


/**
 * Created by Ankit
 */


class LoginRegisterRepository @Inject constructor(
        private val remoteDataSource: LoginRegisterRemoteDataSource
) : LoginRegisterDataSource {

    override suspend fun registerUser(email: String, password: String) =
            remoteDataSource.registerUser(email, password)

    override suspend fun loginUser(email: String, password: String) =
            remoteDataSource.loginUser(email, password)

}