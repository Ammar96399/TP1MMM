package com.example.tp1_mmm.vm_factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tp1_mmm.db.AppDatabase
import com.example.tp1_mmm.viewmodels.RecyclerListViewModel
import com.example.tp1_mmm.views.MainActivity

// Code inspired by https://stackoverflow.com/questions/46283981/android-viewmodel-additional-arguments

// Override ViewModelProvider.NewInstanceFactory to create the ViewModel (VM).
class RecyclerListViewModelFactory(private val db: AppDatabase): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = RecyclerListViewModel(db) as T
}


open class ArgedFragment: Fragment() {
    // Create VM in activity/fragment with VM factory.
    val dbViewModel: RecyclerListViewModel by viewModels { RecyclerListViewModelFactory(
        (requireActivity() as MainActivity).database
    ) }
}