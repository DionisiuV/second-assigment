package com.valentin.secondhomework.view.screenOne

import android.view.View
import com.valentin.secondhomework.R
import com.valentin.secondhomework.model.data.RandomItem
import com.valentin.secondhomework.view.genericAdapter.GenericBindingInterface
import kotlinx.android.synthetic.main.item_compact.view.*

class CompactViewHolder : GenericBindingInterface<RandomItem> {
    override fun bindData(item: RandomItem, view: View, position: Int) {
        setText(item, view)
        setItemPosition(item, position)
    }

    override fun getLayoutResId(): Int {
        return R.layout.item_compact
    }

    private fun setText(item: RandomItem, view: View) {
        view.itemCompactColorTv.text = item.color
        view.itemCompactNameTv.text = item.name
    }

    private fun setItemPosition(item: RandomItem, position: Int) {
        item.position = position
    }
}