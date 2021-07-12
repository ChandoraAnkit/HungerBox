package com.androidy.hungerbox.commons.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.navigateUp
import com.androidy.hungerbox.commonUi.R


fun Fragment.getAppCompatActivity() =
        requireActivity() as AppCompatActivity

fun Fragment.getDrawerLayout(): DrawerLayout =
        requireActivity().findViewById(R.id.drawer_layout)

fun Fragment.setUpToolbar(toolbar: Toolbar,
                          @IdRes vararg topLevelFragments: Int,
                          drawerLayout: DrawerLayout? = null) {

    if (topLevelFragments.isEmpty())
        setupWithNavController(toolbar, findNavController())
    else {

        //Manually syncing the state of toolbar prevent flickering of toolbar icon.
        val appBarConfiguration: AppBarConfiguration
        if (drawerLayout == null){
            toolbar.navigationIcon = null
            appBarConfiguration = AppBarConfiguration(topLevelFragments.toSet())
        }else{
            toolbar.navigationIcon = DrawerArrowDrawable(toolbar.context)
            appBarConfiguration = AppBarConfiguration(topLevelFragments.toSet(), drawerLayout)
        }

        val navController = findNavController()
        toolbar.title = navController.currentDestination?.label
        toolbar.setNavigationOnClickListener { navController.navigateUp(appBarConfiguration) }
    }
}





