package com.androidy.hungerbox.navigation.di


import androidx.navigation.NavController
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class NavigationModule(private val navController: NavController) {

    @Provides
    fun provideNavController(): NavController{
        return navController
    }
}