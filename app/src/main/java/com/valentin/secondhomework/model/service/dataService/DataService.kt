package com.valentin.secondhomework.model.service.dataService

import androidx.lifecycle.MutableLiveData
import com.valentin.secondhomework.model.data.RandomItem

interface DataService {

    fun getData(): MutableLiveData<List<RandomItem>>

    fun setData(randomItems: List<RandomItem>)
}