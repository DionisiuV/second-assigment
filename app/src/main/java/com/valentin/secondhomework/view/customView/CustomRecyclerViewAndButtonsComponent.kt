package com.valentin.secondhomework.view.customView

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.valentin.secondhomework.R
import com.valentin.secondhomework.model.data.RandomItem
import com.valentin.secondhomework.view.customViewHolder.CompactViewHolder
import com.valentin.secondhomework.view.customViewHolder.FullViewHolder
import com.valentin.secondhomework.view.genericAdapter.GenericAdapter
import com.valentin.secondhomework.view.genericAdapter.GenericBindingInterface
import kotlinx.android.synthetic.main.custom_ui_component.*
import kotlinx.android.synthetic.main.custom_ui_component.view.*
import kotlinx.android.synthetic.main.fragment_screen_one.*
@Suppress("UNCHECKED_CAST")
class CustomRecyclerViewAndButtonsComponent : ConstraintLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : this(context, attributeSet, defStyle, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int, defRes: Int) : super(context, attributeSet, defStyle, defRes) {
        inflate(context, R.layout.custom_ui_component, this)

        parseAttributes(attributeSet)
        setupRecyclerView()
        setButtonsVisibility()
    }

    var customViewIsCompact = true


    private fun parseAttributes(attributeSet: AttributeSet?) {
        attributeSet?.let {
            val customUiComponentAttrSet: TypedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomRecyclerViewAndButtonsComponent)

            customViewIsCompact = customUiComponentAttrSet.getBoolean(R.styleable.CustomRecyclerViewAndButtonsComponent_isCompact, true)
            customUiComponentAttrSet.recycle()
        }
    }

    private fun setupRecyclerView() {
        if (adapterIsNotAttached())
            setRecyclerView()
    }

    private fun setRecyclerView() {
        customComponentRV.layoutManager = LinearLayoutManager(context)
        customComponentRV.adapter = getAdapter()
    }

    fun setCompactViewClickEvents(navigateToSecondFragment: () -> Unit, refreshAdapterData: () -> Unit) {
        customComponentSeeMoreC.setOnClickListener { navigateToSecondFragment() }
        customComponentRefreshListC.setOnClickListener { refreshAdapterData() }
    }

    fun setFullViewClickEvents(refreshAdapterData: () -> Unit) {
        customComponentRefreshListF.setOnClickListener { refreshAdapterData() }
    }

    fun updateAdapterDataSet(randomItems: List<RandomItem>? = null) {
        val adapter = (customComponentRV.adapter as GenericAdapter<RandomItem>)

        if (randomItems.isNullOrEmpty())
            adapter.resetDataSet(emptyList())
        else
            adapter.resetDataSet(randomItems)
    }

    private fun adapterIsNotAttached(): Boolean {
        return customComponentRV.adapter == null
    }

    private fun getAdapter(randomItemList: List<RandomItem> = listOf()): GenericAdapter<RandomItem> {
        return GenericAdapter.Builder<RandomItem>()
            .setDataSet(randomItemList)
            .setViewHolder(getViewHolder())
            .build()
    }

    private fun getViewHolder(): GenericBindingInterface<RandomItem> {
        if (customViewIsCompact)
            return CompactViewHolder()

        return FullViewHolder()
    }

    private fun setButtonsVisibility() {
        if (customViewIsCompact)
            setButtonVisibilityForCompactView()
        else
            setButtonVisibilityForFullView()
    }

    private fun setButtonVisibilityForCompactView() {
        customComponentRefreshListF.visibility = View.GONE
        customComponentSeeMoreC.visibility = View.VISIBLE
        customComponentRefreshListC.visibility = View.VISIBLE
    }

    private fun setButtonVisibilityForFullView() {
        customComponentRefreshListF.visibility = View.VISIBLE
        customComponentSeeMoreC.visibility = View.GONE
        customComponentRefreshListC.visibility = View.GONE
    }
}