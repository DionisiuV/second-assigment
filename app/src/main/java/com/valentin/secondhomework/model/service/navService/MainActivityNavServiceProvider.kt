package com.valentin.secondhomework.model.service.navService

import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
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

    override fun handleBackPressedCallBack(currentLocationId: Int) {

        mainActivity.onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {

            override fun handleOnBackPressed() {
                Log.d("DEBUG_TAG", currentLocationId.toString())
                handleBackBasedOnCurrentLocation(currentLocationId)
            }
        })
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

    private fun handleBackBasedOnCurrentLocation(currentLocationId: Int) {
        when (currentLocationId) {
            R.id.screenOneFragment -> showCloseAppAlertDialog()
            R.id.screenTwoFragment -> goToFirstFragment()
            else -> throw Exception("Unhandled nav route!")
        }
    }

    private fun goToFirstFragment() {
        navigateTo(R.id.action_screenTwoFragment_to_screenOneFragment)
    }

    private fun showCloseAppAlertDialog() {
        AlertDialog.Builder(mainActivity).setMessage(R.string.close_app_dialog)
            .setPositiveButton(R.string.close_app_dialog_positive_btn) { _, _ -> closeApp() }
            .setNegativeButton(R.string.close_app_dialog_negative_btn) { _, _ -> }
            .show()
    }

    private fun closeApp() {
        mainActivity.finish()
    }
}