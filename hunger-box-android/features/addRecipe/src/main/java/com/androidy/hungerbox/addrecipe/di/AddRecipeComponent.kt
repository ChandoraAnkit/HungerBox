package com.androidy.hungerbox.addrecipe.di

import com.androidy.hungerbox.addrecipe.ui.AddRecipeFragment
import com.androidy.hungerbox.core.di.BaseFragmentComponent
import com.androidy.hungerbox.core.di.CoreComponent
import com.androidy.hungerbox.core.di.scopes.FeatureScope
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AddRecipeModule::class], dependencies = [CoreComponent::class])
@FeatureScope
interface AddRecipeComponent: BaseFragmentComponent<AddRecipeFragment> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun addRecipeFragment(fragment: AddRecipeFragment): Builder
        fun coreComponent(coreComponent: CoreComponent): Builder

        fun build(): AddRecipeComponent
    }
}