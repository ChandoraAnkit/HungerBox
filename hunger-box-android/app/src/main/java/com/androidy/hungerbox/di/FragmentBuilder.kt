package com.androidy.hungerbox.di

import com.androidy.hungerbox.ui.addRecipe.AddRecipeFragment
import com.androidy.hungerbox.ui.addRecipe.AddRecipeModule
import com.androidy.hungerbox.ui.addRecipe.AddRecipeStepsFragmentBuilder
import com.androidy.hungerbox.ui.explore.ExploreFragment
import com.androidy.hungerbox.ui.explore.ExploreModule
import com.androidy.hungerbox.ui.feeds.FeedsFragment
import com.androidy.hungerbox.ui.feeds.FeedsModule
import com.androidy.hungerbox.ui.loginRegister.login.LoginFragment
import com.androidy.hungerbox.ui.loginRegister.LoginRegisterModule
import com.androidy.hungerbox.ui.loginRegister.register.RegisterFragment
import com.androidy.hungerbox.ui.notifications.NotificationsFragment
import com.androidy.hungerbox.ui.notifications.NotificationsModule
import com.androidy.hungerbox.ui.profile.ProfileFragment
import com.androidy.hungerbox.ui.profile.ProfileModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Ankit
 */


@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [FeedsModule::class])
    abstract fun bindHomeFragment(): FeedsFragment

    @ContributesAndroidInjector(modules = [ProfileModule::class])
    abstract fun bindProfileFragment(): ProfileFragment

    @ContributesAndroidInjector(modules = [NotificationsModule::class])
    abstract fun bindNotificationsFragment(): NotificationsFragment

    @ContributesAndroidInjector(modules = [ExploreModule::class])
    abstract fun bindExploreFragment(): ExploreFragment

    @ContributesAndroidInjector(modules = [LoginRegisterModule::class])
    abstract fun bindLoginFragment(): LoginFragment

    @ContributesAndroidInjector(modules = [LoginRegisterModule::class])
    abstract fun bindRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector(modules = [AddRecipeModule::class, AddRecipeStepsFragmentBuilder::class])
    abstract fun bindAddRecipeFragment(): AddRecipeFragment

}