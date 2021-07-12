package com.androidy.hungerbox.navigation

sealed class NavigationFlow {

    object AuthMainFlow: NavigationFlow()
    object FeedsMainFlow: NavigationFlow()

}