package com.androidy.hungerbox.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidy.hungerbox.ui.addRecipe.AddRecipeViewModel
import com.androidy.hungerbox.ui.explore.ExploreViewModel
import com.androidy.hungerbox.ui.feeds.FeedsViewModel
import com.androidy.hungerbox.ui.loginRegister.LoginRegisterViewModel
import com.androidy.hungerbox.ui.notifications.NotificationsViewModel
import com.androidy.hungerbox.ui.profile.ProfileViewModel
import com.androidy.hungerbox.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 * Created by Ankit
 */


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginRegisterViewModel::class)
    abstract fun loginRegisterViewModel(viewModel: LoginRegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FeedsViewModel::class)
    abstract fun feedsViewModel(viewModel: FeedsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExploreViewModel::class)
    abstract fun exploreViewModel(viewModel: ExploreViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddRecipeViewModel::class)
    abstract fun addRecipeViewModel(viewModel: AddRecipeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NotificationsViewModel::class)
    abstract fun notificationsViewModel(viewModel: NotificationsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun profileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    abstract fun viewModelFactory(viewModelFactory: ViewModelFactory)
            : ViewModelProvider.Factory

}

