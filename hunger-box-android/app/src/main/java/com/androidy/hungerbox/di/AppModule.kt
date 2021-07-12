package com.androidy.hungerbox.di

import android.content.Context
import com.androidy.hungerbox.HungerBoxApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: HungerBoxApplication): Context = application.applicationContext
}