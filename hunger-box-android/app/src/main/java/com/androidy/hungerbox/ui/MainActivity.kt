package com.androidy.hungerbox.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.androidy.hungerbox.R
import com.androidy.hungerbox.utils.ext.gone
import com.androidy.hungerbox.utils.ext.show
import com.google.firebase.auth.FirebaseAuth
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    @Inject
    lateinit var mAuth: FirebaseAuth

    @Inject
    lateinit var mAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        setUpNavigationDrawer()

        navController = findNavController(R.id.navHostFragment)

        bottomNavView.setupWithNavController(navController)
        navView.setupWithNavController(navController)

        setDestinationChangedListener()

        if (mAuth.currentUser == null)
            navController.navigate(R.id.action_navigation_feeds_to_navigation_register_graph)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setDestinationChangedListener() {
        navController.addOnDestinationChangedListener { _, dest, _ ->
            when (dest.id) {
                R.id.navigation_add_recipe_fragment,
                R.id.navigation_register_fragment ->
                    hideTopLevelUi()

                R.id.navigation_login_fragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    hideTopLevelUi()
                }
                else -> {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED)
                    bottomNavView.show()
                }

            }
        }
    }


    private fun hideTopLevelUi() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        bottomNavView.gone()
    }

    private fun setUpNavigationDrawer() {
        drawerLayout.apply {
            setStatusBarBackground(R.color.colorPrimaryDark)
        }
    }

    override fun androidInjector() = mAndroidInjector

}
