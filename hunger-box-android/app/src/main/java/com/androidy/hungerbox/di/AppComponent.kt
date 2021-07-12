package com.androidy.hungerbox.di

import android.app.Application
import com.androidy.hungerbox.HungerBoxApplication
import com.androidy.hungerbox.core.di.CoreComponent
import com.androidy.hungerbox.core.di.scopes.AppScope
import dagger.Component
import javax.inject.Singleton

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {

    fun inject(application: HungerBoxApplication)
}