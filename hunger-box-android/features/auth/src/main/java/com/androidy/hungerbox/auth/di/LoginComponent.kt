package com.androidy.hungerbox.auth.di

import com.androidy.hungerbox.auth.ui.login.LoginFragment
import com.androidy.hungerbox.core.di.BaseFragmentComponent
import com.androidy.hungerbox.core.di.CoreComponent
import com.androidy.hungerbox.core.di.scopes.FragmentScope
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AuthModule::class], dependencies = [CoreComponent::class])
@FragmentScope
interface LoginComponent: BaseFragmentComponent<LoginFragment> {

    @Component.Builder
    interface Builder {

        fun build(): LoginComponent

        @BindsInstance
        fun loginFragment(fragment: LoginFragment): Builder
        fun coreComponent(component: CoreComponent): Builder
    }
}