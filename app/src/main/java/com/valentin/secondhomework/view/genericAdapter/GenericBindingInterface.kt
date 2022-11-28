package com.valentin.secondhomework.view.genericAdapter

import android.view.View

interface GenericBindingInterface<T> {

    fun bindData(item: T, view: View, position: Int)

    fun getLayoutResId(): Int
}