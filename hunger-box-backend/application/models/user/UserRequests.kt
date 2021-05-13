package com.androidy.hungerbox.models.user

import com.androidy.hungerbox.base.BaseRequest
import com.androidy.hungerbox.utils.FailureMessages
import kotlinx.serialization.Serializable



@Serializable
data class CreateUserRequest(
    val email: String
): BaseRequest {

    override fun validate(): Pair<Boolean, String> {
        return Pair(true, "")
    }
}

@Serializable
data class UpdateUserProfileRequest(
    val userName: String? = null,
    val name: String? = null,
    val profileImage: String? = null
): BaseRequest{

    override fun validate(): Pair<Boolean, String> {
        if (userName == null && name == null && profileImage == null)
            return Pair(false, FailureMessages.ARGUMENT_MISSING)

        return Pair(true, "")
    }
}