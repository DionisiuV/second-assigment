package com.valentin.secondhomework.services.navService

import com.valentin.secondhomework.view.activity.main.MainActivity

interface MainActivityNavService {

    fun setupNavService(activity: MainActivity)

    fun navigateTo(destinationOrAction: Int)

    fun getCurrentLocation(): String
}