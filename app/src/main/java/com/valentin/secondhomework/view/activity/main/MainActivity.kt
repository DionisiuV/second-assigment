package com.valentin.secondhomework.view.activity.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.valentin.secondhomework.R
import org.koin.java.KoinJavaComponent.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainActivityViewModel by inject(MainActivityViewModel::class.java)


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
            "fragment_screen_one" -> showCloseAppAlertDialog()
            "fragment_screen_two" -> goToFirstFragment()
            else -> throw Exception("Unhandled nav route!")
        }
    }

    private fun showCloseAppAlertDialog() {
        buildCloseAppAlertDialog().show()
    }

    private fun buildCloseAppAlertDialog(): AlertDialog {
        return AlertDialog.Builder(this).setMessage(R.string.close_app_dialog)
            .setPositiveButton(R.string.close_app_dialog_positive_btn) { _, _ -> closeApp() }
            .setNegativeButton(R.string.close_app_dialog_negative_btn) { _, _ -> }
            .create()
    }

    private fun closeApp() {
        finish()
    }

    private fun goToFirstFragment() {
        viewModel.goToFirstFragment()
    }

    private fun getCurrentLocation(): String {
        return viewModel.getCurrentLocation()
    }

}