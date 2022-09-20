package com.example.tp1_mmm.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tp1_mmm.viewmodels.ItemViewModel

@BindingAdapter("itemViewModels")
fun bindItemViewModels(recyclerView: RecyclerView, itemViewModels: List<ItemViewModel>?) {
    val adapter = getOrCreateAdapter(recyclerView)
    adapter.updateItems(itemViewModels)
}

private fun getOrCreateAdapter(recyclerView: RecyclerView): BindableRecyclerListViewAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is BindableRecyclerListViewAdapter) {
        recyclerView.adapter as BindableRecyclerListViewAdapter
    } else {
        val bindableRecyclerAdapter = BindableRecyclerListViewAdapter()
        recyclerView.adapter = bindableRecyclerAdapter
        bindableRecyclerAdapter
    }
}