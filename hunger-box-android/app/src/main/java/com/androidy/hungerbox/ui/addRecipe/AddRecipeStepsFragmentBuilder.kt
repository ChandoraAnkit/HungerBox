package com.androidy.hungerbox.ui.addRecipe

import com.androidy.hungerbox.ui.addRecipe.steps.addItem.AddItemFragment
import com.androidy.hungerbox.ui.addRecipe.steps.RecipeDescriptionFragment
import com.androidy.hungerbox.ui.addRecipe.steps.RecipePreviewFragment
import com.androidy.hungerbox.ui.addRecipe.steps.RecipeServingDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Ankit
 */


@Module
abstract class AddRecipeStepsFragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindRecipeNameFragment(): RecipeDescriptionFragment

    @ContributesAndroidInjector
    abstract fun bindRecipeServingDetailsFragment(): RecipeServingDetailsFragment

    @ContributesAndroidInjector
    abstract fun bindRecipeIngredientsFragment(): AddItemFragment

    @ContributesAndroidInjector
    abstract fun bindRecipePreviewFragment(): RecipePreviewFragment
}