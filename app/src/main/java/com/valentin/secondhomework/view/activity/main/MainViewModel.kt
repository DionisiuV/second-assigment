package com.valentin.secondhomework.view.activity.main

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.valentin.secondhomework.model.service.navService.MainActivityNavService

class MainViewModel(
    private val navService: MainActivityNavService
) : ViewModel() {

    fun setupNavService(activity: MainActivity) {
        navService.setupNavService(activity)
    }

    fun getNavController(): NavController {
        return navService.getNavController()
    }

    fun handleBackPressedCallBack(currentLocationId: Int) {
        navService.handleBackPressedCallBack(currentLocationId)
    }

}