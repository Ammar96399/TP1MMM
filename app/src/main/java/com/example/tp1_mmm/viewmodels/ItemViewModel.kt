package com.example.tp1_mmm.viewmodels

import androidx.lifecycle.ViewModel
import com.example.tp1_mmm.R

class ItemViewModel
    (val id: Int, val firstname: String, val lastname: String, val birthPlace: String): ViewModel() {
        val layoutId: Int
            get() = R.layout.layout_recycler_list_item
        val viewType: Int
            get() = 0
    }