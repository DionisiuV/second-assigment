package com.valentin.secondhomework.view.fragment.screenOne

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.valentin.secondhomework.R
import com.valentin.secondhomework.base.BaseFragment
import com.valentin.secondhomework.model.data.RandomItem
import kotlinx.android.synthetic.main.custom_ui_component.*
import kotlinx.android.synthetic.main.fragment_screen_one.*
import org.koin.java.KoinJavaComponent.inject

@Suppress("UNCHECKED_CAST")
class ScreenOneFragment : BaseFragment(R.layout.fragment_screen_one) {

    private val viewModel: ScreenOneViewModel by inject(ScreenOneViewModel::class.java)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModelObservers()
        loadDataFromDataSources()
        setClickEvents()
    }

    override fun onBackPressedAction(): OnBackPressedCallback {
        return object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showCloseAppAlertDialog()
            }
        }
    }

    private fun showCloseAppAlertDialog() {
        buildCloseAppAlertDialog().show()
    }

    private fun buildCloseAppAlertDialog(): AlertDialog {
        return AlertDialog.Builder(requireContext()).setMessage(R.string.close_app_dialog)
            .setPositiveButton(R.string.close_app_dialog_positive_btn) { _, _ -> closeApp() }
            .setNegativeButton(R.string.close_app_dialog_negative_btn) { _, _ -> }
            .create()
    }

    private fun closeApp() {
        requireActivity().finish()
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

    private fun loadDataFromDataSources() {
        viewModel.handleUiEvents("loadDataFromDataSources")
    }

    private fun setViewModelObservers() {
        viewModel.setObserver(::observingViewModelEvents)
    }

    private fun setClickEvents() {
        customUiComponent.setCompactViewClickEvents(::navigateToSecondFragment, ::refreshAdapterData)
    }

    private fun refreshAdapterData() {
        customUiComponent.updateAdapterDataSet(emptyList())
        viewModel.handleUiEvents("refreshData")
    }

    private fun isError() {
        hideProgressBar()
        Toast.makeText(requireActivity(), "An error has been occurred...", Toast.LENGTH_SHORT).show()
        enableClickEvents()
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
        customComponentSeeMoreC.isClickable = false
        customComponentRefreshListC.isClickable = false
    }

    private fun enableClickEvents() {
        customComponentSeeMoreC.isClickable = true
        customComponentRefreshListC.isClickable = true
    }

    private fun navigateToSecondFragment() {
        viewModel.handleUiEvents("navigateToSecondFragment")
    }
}

