package com.androidy.hungerbox.data.addRecipe

import com.google.firebase.firestore.PropertyName


/**
 * Created by Ankit
 */


data class RecipeModel(

        @set:PropertyName("user_id")
        @get:PropertyName("user_id")
        var userId: String?,

        @set:PropertyName("recipe_name")
        @get:PropertyName("recipe_name")
        var recipeName: String?,

        @set:PropertyName("recipe_short_description")
        @get:PropertyName("recipe_short_description")
        var description: String?,

        var ingredients: List<Item>?,

        var directions: List<Item>?,

        @set:PropertyName("food_category")
        @get:PropertyName("food_category")
        var foodCategory: String?,

        @set:PropertyName("prep_time")
        @get:PropertyName("prep_time")
        var prepTime: String?,

        @set:PropertyName("cook_time")
        @get:PropertyName("cook_time")
        var cookTime: String?,

        @set:PropertyName("total_time")
        @get:PropertyName("total_time")
        var totalTime: String?,

        @set:PropertyName("created_at")
        @get:PropertyName("created_at")
        var created: String = System.currentTimeMillis().toString(),

        @set:PropertyName("updated_at")
        @get:PropertyName("updated_at")
        var updated: String?  = null
)