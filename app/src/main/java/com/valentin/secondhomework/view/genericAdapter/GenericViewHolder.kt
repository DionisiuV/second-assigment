package com.valentin.secondhomework.view.genericAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

open class GenericViewHolder<T>(itemView: View) : ViewHolder(itemView) {

    fun <T> bindData(item: T, contract: GenericBindingInterface<T>, position: Int) {
        return contract.bindData(item, itemView, position)
    }
}