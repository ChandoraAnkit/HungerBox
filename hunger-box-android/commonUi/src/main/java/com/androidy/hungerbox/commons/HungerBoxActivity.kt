package com.androidy.hungerbox.commons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.androidy.hungerbox.commonUi.R
import com.androidy.hungerbox.commons.extensions.gone
import com.androidy.hungerbox.commons.extensions.show

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_hunger_box.*

import javax.inject.Inject

class HungerBoxActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    @Inject
    lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hunger_box)

        navController = findNavController(R.id.nav_host_fragment)

        initNavComponent()

        setUpNavigationDrawer()

        bottom_nav_view.setupWithNavController(navController)
        nav_view.setupWithNavController(navController)

        setDestinationChangedListener()

        if (mAuth.currentUser == null)
            navController.navigate(R.id.action_global_to_navigation_auth)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun initNavComponent(){
//        DaggerNavigationComponent
//            .builder()
//            .navigationModule(NavigationModule(navController))
//            .build()
    }

    private fun setDestinationChangedListener() {
        navController.addOnDestinationChangedListener { _, dest, _ ->
            when (dest.id) {
                R.id.navigation_add_recipe,
                R.id.navigation_auth -> hideTopLevelUi()

                else -> {
                    drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED)
                    bottom_nav_view.show()
                }

            }
        }
    }


    private fun hideTopLevelUi() {
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        bottom_nav_view.gone()
    }

    private fun setUpNavigationDrawer() {
        drawer_layout.run {
            setStatusBarBackground(R.color.colorPrimaryDark)
        }
    }
}
