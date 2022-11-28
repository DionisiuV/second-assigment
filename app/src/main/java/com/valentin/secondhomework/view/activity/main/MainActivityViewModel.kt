package com.valentin.secondhomework.view.activity.main

import androidx.lifecycle.ViewModel
import com.valentin.secondhomework.R
import com.valentin.secondhomework.services.navService.MainActivityNavService

class MainActivityViewModel(
    private val navService: MainActivityNavService
) : ViewModel() {

    private fun navigateTo(destinationOrActionId: Int) {
        navService.navigateTo(destinationOrActionId)
    }

    fun setupNavService(activity: MainActivity) {
        navService.setupNavService(activity)
    }

    fun goToFirstFragment() {
        navigateTo(R.id.action_screenTwoFragment_to_screenOneFragment)
    }

    fun getCurrentLocation(): String {
        return navService.getCurrentLocation()
    }
}