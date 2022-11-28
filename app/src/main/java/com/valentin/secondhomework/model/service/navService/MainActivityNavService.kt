package com.valentin.secondhomework.model.service.navService

import androidx.navigation.NavController
import com.valentin.secondhomework.view.activity.main.MainActivity

interface MainActivityNavService {

    fun setupNavService(activity: MainActivity)

    fun getNavController(): NavController

    fun navigateTo(destinationOrAction: Int)
}