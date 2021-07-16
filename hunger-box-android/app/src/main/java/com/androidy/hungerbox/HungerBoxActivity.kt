package com.androidy.hungerbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.androidy.hungerbox.commons.extensions.gone
import com.androidy.hungerbox.commons.extensions.show
import com.androidy.hungerbox.databinding.ActivityHungerBoxBinding

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_hunger_box.*

import javax.inject.Inject

class HungerBoxActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHungerBoxBinding

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

//    @Inject
//    lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHungerBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment)

        initNavComponent()
        setUpNavigationDrawer()

        binding.bottomNavView.setupWithNavController(navController)
        binding.navView.setupWithNavController(navController)

        setDestinationChangedListener()

//        if (mAuth.currentUser == null)
//            navController.navigate(R.id.action_global_to_navigation_auth)
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
//            .navController(navController)
//            .build()
    }

    private fun setDestinationChangedListener() {
        navController.addOnDestinationChangedListener { _, dest, _ ->
            when (dest.id) {
                R.id.navigation_add_recipe,
                R.id.navigation_auth -> hideTopLevelUi()

                else -> {
                    binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED)
                    binding.bottomNavView.show()
                }

            }
        }
    }


    private fun hideTopLevelUi() {
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        binding.bottomNavView.gone()
    }

    private fun setUpNavigationDrawer() {
        binding.drawerLayout.run {
            setStatusBarBackground(R.color.colorPrimaryDark)
        }
    }
}
