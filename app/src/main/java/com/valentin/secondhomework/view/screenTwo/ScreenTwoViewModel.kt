package com.valentin.secondhomework.view.screenTwo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.valentin.secondhomework.model.data.RandomItem
import com.valentin.secondhomework.model.service.dataService.DataService
import com.valentin.secondhomework.model.service.navService.MainActivityNavService

class ScreenTwoViewModel(
    private val dataService: DataService,
    private val navService: MainActivityNavService
) : ViewModel() {
    fun getData(): LiveData<List<RandomItem>> {
        return dataService.getData()
    }
}