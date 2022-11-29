package com.valentin.secondhomework.model.service.navService

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.valentin.secondhomework.R
import com.valentin.secondhomework.view.activity.main.MainActivity

class MainActivityNavServiceProvider : MainActivityNavService {

    private lateinit var mainActivity: MainActivity
    private lateinit var navController: NavController


    override fun setupNavService(activity: MainActivity) {
        if (shouldSetupNav())
            initNavService(activity)
        else
            throw Exception("Nav for MainActivity was already setup!")
    }

    override fun getNavController(): NavController {
        return navController
    }

    override fun navigateTo(destinationOrAction: Int) {
        navController.navigate(destinationOrAction)
    }

    private fun initNavService(activity: MainActivity) {
        initMainActivity(activity)

        if (shouldSetupNavController())
            setupNavController()
    }

    private fun initMainActivity(activity: MainActivity) {
        this.mainActivity = activity
    }

    private fun setupNavController() {
        navController = getNavHost().navController
        navController.graph = navController.navInflater.inflate(getNavGraph())
    }

    private fun shouldSetupNavController(): Boolean {
        return !this::navController.isInitialized
    }

    private fun shouldSetupNav(): Boolean {
        return !this::mainActivity.isInitialized
    }

    private fun getNavHost(): NavHostFragment {
        return mainActivity.supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
}


    private fun getNavGraph(): Int {
        return R.navigation.nav_graph
    }
}