package com.studentlifemanager.expense.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("data")
fun <T> setRecyclerViewData(recyclerView: RecyclerView, data: T) {
    data?.let {
        if (recyclerView.adapter is BindableAdapter<*>) {
            (recyclerView as BindableAdapter<T>).setData(data)
        }
    }
}