package com.valentin.secondhomework.view.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.valentin.secondhomework.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //move to nav service
        setNav()
    }

    //move to nav service
    fun setNav() {
        val navHost = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navHost.navController.graph = navHost.navController.navInflater.inflate(R.navigation.nav_graph)
    }
}