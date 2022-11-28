package com.valentin.secondhomework.view.activity.main

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.valentin.secondhomework.R
import com.valentin.secondhomework.model.service.navService.MainActivityNavService

class MainViewModel(
    private val navService: MainActivityNavService
) : ViewModel() {

    private fun navigateTo(destinationOrActionId: Int) {
        navService.navigateTo(destinationOrActionId)
    }

    fun setupNavService(activity: MainActivity) {
        navService.setupNavService(activity)
    }

    fun getNavController(): NavController {
        return navService.getNavController()
    }

    fun goToFirstFragment() {
        navigateTo(R.id.action_screenTwoFragment_to_screenOneFragment)
    }
}