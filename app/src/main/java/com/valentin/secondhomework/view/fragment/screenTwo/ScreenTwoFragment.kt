package com.valentin.secondhomework.view.fragment.screenTwo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.valentin.secondhomework.R
import com.valentin.secondhomework.base.BaseFragment
import com.valentin.secondhomework.model.data.RandomItem
import kotlinx.android.synthetic.main.custom_ui_component.*
import kotlinx.android.synthetic.main.fragment_screen_one.*
import org.koin.java.KoinJavaComponent.inject

class ScreenTwoFragment : BaseFragment(R.layout.fragment_screen_two) {

    private val viewModel: ScreenTwoViewModel by inject(ScreenTwoViewModel::class.java)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModelObservers()
        loadDataFromDataSources()
        setClickEvents()
    }

    override fun onBackPressedAction(): OnBackPressedCallback {
        return object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.goToFirstFragment()
            }
        }
    }

    private fun observingViewModelEvents(eventType: String, data: Any?) {
        when (eventType) {
            "gotElementsFromApi" -> isSuccess(data as List<RandomItem>)
            "gotElementsError" -> isError()
            "gotElementsLoading" -> isLoading()
            "gotElementsFromCache" -> isSuccess(data as List<RandomItem>)
            else -> throw Exception("Unhandled View event")
        }
    }

    private fun setViewModelObservers() {
        viewModel.setObserver(::observingViewModelEvents)
    }

    private fun loadDataFromDataSources() {
        viewModel.handleUiEvents("loadDataFromDataSources")
    }


    private fun refreshAdapterData() {
        customUiComponent.updateAdapterDataSet(emptyList())
        viewModel.handleUiEvents("refreshData")
    }

    private fun setClickEvents() {
        customUiComponent.setFullViewClickEvents(::refreshAdapterData)
    }

    private fun isError() {
        hideProgressBar()
        enableClickEvents()
        Toast.makeText(requireActivity(), "An error has been occurred...", Toast.LENGTH_SHORT).show()
    }

    private fun isSuccess(dataSet: List<RandomItem>) {
        customUiComponent.updateAdapterDataSet(dataSet)
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

    private fun disableClickEvents() {
        customComponentRefreshListF.isClickable = false
    }

    private fun enableClickEvents() {
        customComponentRefreshListF.isClickable = true
    }
}