package com.valentin.secondhomework.view.screenOne

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.valentin.secondhomework.R
import com.valentin.secondhomework.model.data.ApiResponse
import com.valentin.secondhomework.model.data.RandomItem
import com.valentin.secondhomework.view.genericAdapter.GenericAdapter
import kotlinx.android.synthetic.main.custom_ui_component.*
import kotlinx.android.synthetic.main.custom_ui_component.view.*
import kotlinx.android.synthetic.main.fragment_screen_one.*
import org.koin.java.KoinJavaComponent.inject

class ScreenOneFragment : Fragment(R.layout.fragment_screen_one) {

    private val viewModel: ScreenOneViewModel by inject(ScreenOneViewModel::class.java)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        updateRecyclerView()
        setButtonsToBeDisplayed()
    }

    private fun updateRecyclerView() {
        if (adapterIsNotAttached())
            getRandomItemsAndTryToSetAdapter()
    }

    private fun setButtonsToBeDisplayed() {
        setButtonsVisibility()
        setClickEvents()
    }

    private fun getRandomItemsAndTryToSetAdapter() {
        viewModel.getRandomItems().observe(viewLifecycleOwner) { observeApiResponse(it) }
    }


    private fun refreshAdapterData() {
        customComponentRVC.adapter = null
        viewModel.refreshData().observe(viewLifecycleOwner) { observeApiResponse(it) }
    }

    private fun observeApiResponse(apiResponse: ApiResponse<List<RandomItem>>) {
        when (apiResponse) {
            is ApiResponse.Loading -> isLoading()
            is ApiResponse.Error -> isError()
            is ApiResponse.Success -> isSuccess(apiResponse.dataSet)
        }
    }

    private fun isError() {
        hideProgressBar()
        Toast.makeText(requireActivity(), "An error has been occurred...", Toast.LENGTH_SHORT).show()
        enableClickEvents()
    }

    private fun isSuccess(dataSet: List<RandomItem>) {
        initAdapter(dataSet)
        hideProgressBar()
        enableClickEvents()
    }

    private fun isLoading() {
        disableClickEvents()
        showProgressBar()
    }

    private fun showProgressBar() {
        customComponentProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        customComponentProgressBar.visibility = View.GONE
    }

    private fun setButtonsVisibility() {
        customComponentRefreshListF.visibility = View.GONE
        customComponentSeeMoreC.visibility = View.VISIBLE
        customComponentRefreshListC.visibility = View.VISIBLE
    }

    private fun disableClickEvents() {
        customComponentSeeMoreC.isClickable = false
        customComponentRefreshListC.isClickable = false
    }

    private fun enableClickEvents() {
        customComponentSeeMoreC.isClickable = true
        customComponentRefreshListC.isClickable = true
    }

    private fun setClickEvents() {
        customComponentSeeMoreC.setOnClickListener {
            navigateToSecondFragment()
        }

        customComponentRefreshListC.setOnClickListener { refreshAdapterData() }
    }

    private fun navigateToSecondFragment() {
        viewModel.goToSecondFragment()
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

    private fun getViewHolder(): CompactViewHolder {
        return CompactViewHolder()
    }
}

