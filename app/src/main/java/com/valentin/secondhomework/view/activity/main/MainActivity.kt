package com.valentin.secondhomework.view.activity.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.valentin.secondhomework.R
import org.koin.java.KoinJavaComponent.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by inject(MainViewModel::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNav()
        handleOnBackPressed()
    }

    private fun initNav() {
        viewModel.setupNavService(this)
    }

    private fun handleOnBackPressed() {
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackBasedOnCurrentLocation(getCurrentLocation())
            }
        })
    }

    private fun handleBackBasedOnCurrentLocation(currentLocation: String) {
        when (currentLocation) {
            "fragment_screen_one" -> closeApp()
            "fragment_screen_two" -> goToFirstFragment()
            else -> throw Exception("Unhandled nav route!")
        }
    }

    private fun closeApp() {
        finish()
    }

    private fun goToFirstFragment() {
        navigateTo(R.id.action_screenTwoFragment_to_screenOneFragment)
    }

    private fun getCurrentLocation(): String {
        return viewModel.getNavController().currentDestination!!.label.toString()
    }

    private fun navigateTo(destinationOrActionId: Int) {
        viewModel.navigateTo(destinationOrActionId)
    }
}