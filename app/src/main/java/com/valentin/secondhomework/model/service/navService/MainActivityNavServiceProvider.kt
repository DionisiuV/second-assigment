package com.valentin.secondhomework.model.service.navService

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
    }

    override fun navigateTo(destinationOrAction: Int) {
        navController.navigate(destinationOrAction)
    }

    override fun getCurrentLocation(): String {
        return navController.currentDestination!!.label.toString()
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