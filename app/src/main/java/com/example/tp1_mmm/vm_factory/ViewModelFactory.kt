package com.example.tp1_mmm.vm_factory

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tp1_mmm.db.AppDatabase
import com.example.tp1_mmm.viewmodels.RecyclerListViewModel
import com.example.tp1_mmm.views.MainActivity

// Code inspired by https://stackoverflow.com/questions/46283981/android-viewmodel-additional-arguments

// Override ViewModelProvider.NewInstanceFactory to create the ViewModel (VM).
class DBArgedViewModelFactory<TFinalVM: DBArgedViewModel>(private val db: AppDatabase): ViewModelProvider.NewInstanceFactory() {
    fun _create(modelClass: Class<TFinalVM>): TFinalVM =
        modelClass.kotlin.constructors.first { it.parameters.size == 1 }
            .call(db)
    override fun <U : ViewModel> create(modelClass: Class<U>): U = _create(modelClass as Class<TFinalVM>) as U
}

open class DBArgedViewModel(private val db: AppDatabase) : ViewModel() {
    init {
        //TODO: Use 'someString' to init process when VM is created. i.e. Get data request.
    }
}

open class DBArgedFragment<TFinalVM: DBArgedViewModel>: Fragment() {
    // Create VM in activity/fragment with VM factory.
    val dbViewModel: DBArgedViewModel by viewModels { DBArgedViewModelFactory<TFinalVM>(
        (requireActivity() as MainActivity).database
    ) }
}