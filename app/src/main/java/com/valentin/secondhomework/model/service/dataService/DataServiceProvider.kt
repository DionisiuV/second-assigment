package com.valentin.secondhomework.model.service.dataService

import androidx.lifecycle.MutableLiveData
import com.valentin.secondhomework.model.data.RandomItemwq

class DataServiceProvider : DataService {

    private val randomItemList = MutableLiveData<List<RandomItem>>()


    override fun getData(): MutableLiveData<List<RandomItem>> {
        if (isRandomItemListEmpty())
        //if list is empty, get data from the api
            setHardCodedValue()

        return randomItemList
    }

    override fun setData(randomItems: List<RandomItem>) {
        //make api call and post value
    }


    private fun isRandomItemListEmpty(): Boolean {
        return randomItemList.value == null
    }

    private fun setHardCodedValue() {
        randomItemList.value = listOf(
            RandomItem("Item1", "Red", "Desc1"),
            RandomItem("Item2", "Green", "Desc2"),
            RandomItem("Item3", "Blue", "Desc3"),
            RandomItem("Item4", "Red", "Desc4"),
            RandomItem("Item5", "Green", "Desc5"),
            RandomItem("Item6", "Blue", "Desc6")
        )
    }
}