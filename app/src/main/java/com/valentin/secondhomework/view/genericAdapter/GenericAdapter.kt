package com.valentin.secondhomework.view.genericAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter

class GenericAdapter<T> private constructor() : Adapter<GenericViewHolder<T>>() {

    private lateinit var dataSet: List<T>
    private lateinit var contract: GenericBindingInterface<T>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val itemView = LayoutInflater.from(parent.context).inflate(contract.getLayoutResId(), parent, false)

        return GenericViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        holder.bindData(dataSet[position], contract, position)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


    class Builder<T> {

        private val genericAdapter = GenericAdapter<T>()


        fun setDataSet(dataSet: List<T>): Builder<T> {
            genericAdapter.dataSet = dataSet

            return this
        }

        fun setViewHolder(contract: GenericBindingInterface<T>): Builder<T> {
            genericAdapter.contract = contract

            return this
        }

        fun build(): GenericAdapter<T> {
            return genericAdapter
        }
    }
}