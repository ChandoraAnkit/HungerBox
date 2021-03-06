package com.androidy.hungerbox.auth.data.source.remote

import com.androidy.hungerbox.core.data.Result
import com.androidy.hungerbox.auth.data.User
import com.androidy.hungerbox.auth.data.source.LoginRegisterDataSource
import com.androidy.hungerbox.core.utils.Constants
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
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

    override suspend fun loginUser(email: String, password: String): Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.Success(Unit)
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }
}