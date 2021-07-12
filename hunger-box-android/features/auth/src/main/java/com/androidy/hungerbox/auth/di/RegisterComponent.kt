package com.androidy.hungerbox.auth.di

import com.androidy.hungerbox.auth.ui.register.RegisterFragment
import com.androidy.hungerbox.core.di.BaseFragmentComponent
import com.androidy.hungerbox.core.di.CoreComponent
import com.androidy.hungerbox.core.di.scopes.FragmentScope
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AuthModule::class], dependencies = [CoreComponent::class])
@FragmentScope
interface RegisterComponent: BaseFragmentComponent<RegisterFragment> {

    @Component.Builder
    interface Builder {

        fun build(): RegisterComponent

        @BindsInstance
        fun registerFragment(fragment: RegisterFragment): Builder
        fun coreComponent(component: CoreComponent): Builder
    }
}