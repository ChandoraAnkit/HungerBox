package com.androidy.hungerbox.feeds.di

import com.androidy.hungerbox.core.di.BaseFragmentComponent
import com.androidy.hungerbox.core.di.CoreComponent
import com.androidy.hungerbox.core.di.scopes.FeatureScope
import com.androidy.hungerbox.feeds.ui.FeedsFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [FeedsModule::class], dependencies = [CoreComponent::class])
@FeatureScope
interface FeedsComponent: BaseFragmentComponent<FeedsFragment> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun feedsFragment(fragment: FeedsFragment): Builder
        fun coreComponent(coreComponent: CoreComponent): Builder

        fun build(): FeedsComponent
    }
}