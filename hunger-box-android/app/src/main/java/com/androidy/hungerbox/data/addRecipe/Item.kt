package com.androidy.hungerbox.data.addRecipe

import com.google.firebase.firestore.Exclude
import kotlin.random.Random


/**
 * Created by Ankit
 */


data class Item(
        @set: Exclude @get: Exclude
        var id: Long = Random.nextLong(0, 100000),
        @set: Exclude @get: Exclude
        var editable: Boolean = false,
        var value: String
)