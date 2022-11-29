package com.valentin.secondhomework.view.activity.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.valentin.secondhomework.R
import org.koin.java.KoinJavaComponent.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by inject(MainViewModel::class.java)
    private var currentDestinationId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNav()
        onDestinationChangedSetCurrentDestinationId()
    }

    private fun initNav() {
        viewModel.setupNavService(this)
    }

    private fun handleBackPressed() {
        viewModel.handleBackPressedCallBack(currentDestinationId)
    }

    private fun setCurrentDestinationId(currentDestinationId: Int) {
        this.currentDestinationId = currentDestinationId
    }

    private fun onDestinationChangedSetCurrentDestinationId() {
        viewModel.getNavController().addOnDestinationChangedListener { _, destination, _ ->
            setCurrentDestinationId(destination.id)
            handleBackPressed()
        }
    }
}