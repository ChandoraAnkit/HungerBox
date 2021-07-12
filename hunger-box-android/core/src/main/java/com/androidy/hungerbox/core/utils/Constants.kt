package com.androidy.hungerbox.core.utils

object Constants {

    const val USERS = "users"
    const val RECIPES = "recipes"
    const val USER_RECIPES = "user_recipes"


    const val VEG = "veg"
    const val NON_VEG = "non-veg"

    val TIME by lazy {
        mapOf("Minutes" to "Min", "Hours" to "Hr", "Days" to "Days")
    }
}