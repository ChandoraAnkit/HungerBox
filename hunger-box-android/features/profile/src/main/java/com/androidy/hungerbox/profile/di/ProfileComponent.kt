package com.androidy.hungerbox.profile.di

import com.androidy.hungerbox.core.di.BaseFragmentComponent
import com.androidy.hungerbox.core.di.CoreComponent
import com.androidy.hungerbox.core.di.scopes.FeatureScope
import com.androidy.hungerbox.profile.ui.ProfileFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ProfileModule::class], dependencies = [CoreComponent::class])
@FeatureScope
interface ProfileComponent: BaseFragmentComponent<ProfileFragment> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun profileFragment(fragment: ProfileFragment): Builder
        fun coreComponent(coreComponent: CoreComponent): Builder

        fun build(): ProfileComponent
    }
}