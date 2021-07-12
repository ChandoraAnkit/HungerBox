package com.androidy.hungerbox.navigation

import androidx.navigation.NavController
import javax.inject.Inject


class Navigator @Inject constructor(private val navController: NavController) {

    fun navigateToFlow(navigationFlow: NavigationFlow) {
        val directions = when (navigationFlow) {
            NavigationFlow.AuthMainFlow ->
                NavigationMainDirections.actionGlobalToNavigationAuth()
            NavigationFlow.FeedsMainFlow ->
                NavigationMainDirections.actionGlobalToNavigationFeeds()
        }
        navController.navigate(directions)
    }
}