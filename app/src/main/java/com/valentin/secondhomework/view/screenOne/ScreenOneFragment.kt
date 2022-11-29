package com.valentin.secondhomework.view.screenOne

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.valentin.secondhomework.R
import com.valentin.secondhomework.model.data.RandomItem
import com.valentin.secondhomework.view.genericAdapter.GenericAdapter
import kotlinx.android.synthetic.main.custom_ui_component_compact.*
import kotlinx.android.synthetic.main.custom_ui_component_compact.view.*
import kotlinx.android.synthetic.main.fragment_screen_one.*
import org.koin.java.KoinJavaComponent.inject

class ScreenOneFragment : Fragment(R.layout.fragment_screen_one) {

    private val viewModel: ScreenOneViewModel by inject(ScreenOneViewModel::class.java)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateRV()
        setSeeMoreBtn()
    }


    private fun updateRV() {
        //if adapter not attached, make request to return mock data
        viewModel.getData().observe(viewLifecycleOwner) {
            if (adapterIsNotAttached())
                initAdapter(it)
        }
    }

    private fun setSeeMoreBtn() {
        customComponentSeeMoreC.setOnClickListener {
            findNavController().navigate(R.id.action_screenOneFragment_to_screenTwoFragment)
        }
    }

    private fun initAdapter(randomItems: List<RandomItem>) {
        Log.d("DEBUG_TAG", "adapter null, need to init list")
        customUiComponentContainerCompact.customComponentRVC.layoutManager = LinearLayoutManager(activity)
        customUiComponentContainerCompact.customComponentRVC.adapter = getAdapter(randomItems)
    }

    private fun adapterIsNotAttached(): Boolean {
        return customUiComponentContainerCompact.customComponentRVC.adapter == null
    }

    private fun getAdapter(randomItemList: List<RandomItem>): GenericAdapter<RandomItem> {
        return GenericAdapter.Builder<RandomItem>()
            .setDataSet(randomItemList)
            .setViewHolder(getViewHolder())
            .build()
    }

    private fun getViewHolder(): CompactViewHolder {
        return CompactViewHolder()
    }
}

