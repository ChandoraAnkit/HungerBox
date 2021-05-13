package com.androidy.hungerbox.data.loginRegister.source.remote

import com.androidy.hungerbox.data.Result
import com.androidy.hungerbox.data.loginRegister.User
import com.androidy.hungerbox.data.loginRegister.source.LoginRegisterDataSource
import com.androidy.hungerbox.utils.Constants
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


/**
 * Created by Ankit
 */


class LoginRegisterRemoteDataSource @Inject constructor(
        private val auth: FirebaseAuth,
        private val fireStore: FirebaseFirestore) : LoginRegisterDataSource {

    override suspend fun registerUser(email: String, password: String): Result<User> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            authResult.user?.let {
                val user = User(it.uid, it.displayName, it.email, it.phoneNumber)
                fireStore.collection(Constants.USERS).add(user).await()

                Result.Success(user)
            } ?: Result.Error(Exception("User registration failed!"))
        } catch (ex: FirebaseException) {
            Result.Error(ex)
        }
    }

    override suspend fun loginUser(email: String, password: String): Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.Success(Unit)
        } catch (ex: FirebaseException) {
            Result.Error(ex)
        }
    }
}