package com.valentin.secondhomework.view.customUiComponent

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.valentin.secondhomework.R

class CustomUiComponentFull(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    init {
        inflate(context, R.layout.custom_ui_component_full, this)
    }
}