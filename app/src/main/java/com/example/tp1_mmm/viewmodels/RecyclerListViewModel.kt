package com.example.tp1_mmm.viewmodels

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp1_mmm.db.AppDatabase
import com.example.tp1_mmm.models.Person
import com.example.tp1_mmm.vm_factory.DBArgedViewModel
import kotlinx.coroutines.launch

// The implementation of recyclerview /w list is inspired by this article:
// https://proandroiddev.com/flexible-recyclerview-adapter-with-mvvm-and-data-binding-74f75caef66a

class RecyclerListViewModel(db: AppDatabase) : DBArgedViewModel(db), Observable {

    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    override fun addOnPropertyChangedCallback
                (callback: Observable.OnPropertyChangedCallback) { callbacks.add(callback) }

    override fun removeOnPropertyChangedCallback
                (callback: Observable.OnPropertyChangedCallback) { callbacks.remove(callback) }

    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }
    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }

    val datum: LiveData<List<ItemViewModel>>
        get() = _datum

    private val _datum = MutableLiveData<List<ItemViewModel>>(emptyList())

    private val dao = db.personDao()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            val personList = dao.getAll()
            val viewData = createViewData(personList)
            _datum.postValue(viewData)
        }
    }

    private fun createViewData(personList: List<Person>): List<ItemViewModel> {
        return personList.mapIndexed { i, v ->
            ItemViewModel(i, v.firstName, v.lastName, v.birthPlace)
        }
    }
}