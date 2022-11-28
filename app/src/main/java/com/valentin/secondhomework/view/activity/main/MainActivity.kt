package com.valentin.secondhomework.view.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.valentin.secondhomework.R
import org.koin.java.KoinJavaComponent.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainActivityViewModel by inject(MainActivityViewModel::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNav()
    }

    private fun initNav() {
        viewModel.setupNavService(this)
    }
}