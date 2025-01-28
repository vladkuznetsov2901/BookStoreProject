package com.example.bookstoreproject.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.bookstoreproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_activity_main) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home, R.id.nav_cart))

        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.authFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                R.id.welcomeFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                R.id.registrationFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.nav_cart -> {
                    navController.navigate(R.id.cartFragment)
                    true
                }
                else -> false
            }
        }
    }
}
