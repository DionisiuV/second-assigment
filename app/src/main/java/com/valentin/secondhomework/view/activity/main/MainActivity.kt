package com.valentin.secondhomework.view.activity.main

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.valentin.secondhomework.R
import org.koin.java.KoinJavaComponent.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by inject(MainViewModel::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isFirstFragment()) {
                    //close the app
                    Log.d("DEBUG_TAG", "FIRST FRAGMENT, close app")
                } else {
                    // nav back
                    Log.d("DEBUG_TAG", "NAV BACK")
                }
            }

        })
        initNav()
    }

    private fun isFirstFragment(): Boolean {
        return viewModel.getNavController().graph.startDestinationId == viewModel.getNavController().currentDestination?.id
    }

    private fun initNav() {
        viewModel.setupNavService(this)
    }
}