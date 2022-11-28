package com.valentin.secondhomework.view.screenTwo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.valentin.secondhomework.R
import com.valentin.secondhomework.model.data.RandomItem
import com.valentin.secondhomework.view.genericAdapter.GenericAdapter
import kotlinx.android.synthetic.main.custom_ui_component.*
import kotlinx.android.synthetic.main.custom_ui_component.view.*
import kotlinx.android.synthetic.main.fragment_screen_one.*
import org.koin.java.KoinJavaComponent

class ScreenTwoFragment : Fragment(R.layout.fragment_screen_two) {

    private val viewModel: ScreenTwoViewModel by KoinJavaComponent.inject(ScreenTwoViewModel::class.java)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        updateRV()
    }

    private fun updateRV() {
        //if adapter not attached, make request to return mock data
        viewModel.getData().observe(viewLifecycleOwner) {
            if (adapterIsNotAttached())
                initAdapter(it)
        }
    }

    private fun initUi() {
        customComponentRefreshListF.visibility = View.VISIBLE
        customComponentSeeMoreC.visibility = View.GONE
        customComponentRefreshListC.visibility = View.GONE
    }

    private fun initAdapter(randomItems: List<RandomItem>) {
        customUiComponentContainer.customComponentRVC.layoutManager = LinearLayoutManager(activity)
        customUiComponentContainer.customComponentRVC.adapter = getAdapter(randomItems)
    }

    private fun adapterIsNotAttached(): Boolean {
        return customUiComponentContainer.customComponentRVC.adapter == null
    }

    private fun getAdapter(randomItemList: List<RandomItem>): GenericAdapter<RandomItem> {
        return GenericAdapter.Builder<RandomItem>()
            .setDataSet(randomItemList)
            .setViewHolder(getViewHolder())
            .build()
    }

    private fun getViewHolder(): FullViewHolder {
        return FullViewHolder()
    }
}