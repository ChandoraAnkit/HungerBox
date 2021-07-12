package com.androidy.hungerbox.core.di

import android.app.Application
import android.content.Context
import com.androidy.hungerbox.core.di.modules.ContextModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [CoreModule::class, ContextModule::class])
@Singleton
interface CoreComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun context(application: Application): Builder

        fun build(): CoreComponent
    }

}