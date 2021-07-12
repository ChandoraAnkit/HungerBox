package com.androidy.hungerbox.auth.data

import com.google.gson.annotations.SerializedName


/**
 * Created by Ankit
 */


data class User(
        @field:SerializedName("user_id")
        val userId: String,

        @field:SerializedName("name")
        var name: String?,

        @field:SerializedName("email")
        var email: String?,

        @field:SerializedName("phone")
        var phoneNo: String?
)