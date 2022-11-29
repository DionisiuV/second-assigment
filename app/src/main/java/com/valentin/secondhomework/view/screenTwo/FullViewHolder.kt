package com.valentin.secondhomework.view.screenTwo

import android.view.View
import com.valentin.secondhomework.R
import com.valentin.secondhomework.model.data.RandomItem
import com.valentin.secondhomework.view.genericAdapter.GenericBindingInterface
import kotlinx.android.synthetic.main.item_full.view.*

class FullViewHolder : GenericBindingInterface<RandomItem> {
    override fun bindData(item: RandomItem, view: View, position: Int) {

        setText(item, view)
        setItemPosition(item, position)
    }

    override fun getLayoutResId(): Int {
        return R.layout.item_full
    }

    private fun setText(item: RandomItem, view: View) {
        view.itemFullColorTv.text = item.color
        view.itemFullDescriptionTv.text = item.description
        view.itemFullNameTv.text = item.name
    }

    private fun setItemPosition(item: RandomItem, position: Int) {
        item.position = position
    }
}