package com.example.suprememath

import android.os.Bundle
import android.os.Handler
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.suprememath.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        prefs= Prefs(this)
        if (!prefs!!.isShown()){
            navController.navigate(R.id.boardFragment)
        }
        navController.addOnDestinationChangedListener  { navController: NavController, navDestination: NavDestination, bundle: Bundle? ->

            val fragments = arrayListOf(R.id.navigation_home,R.id.navigation_dashboard,R.id.navigation_notifications)
            if (fragments.contains(navDestination.id)){
                binding.navView.visibility = View.VISIBLE
            }else{
                binding.navView.visibility = View.GONE
            }



        }
}
}