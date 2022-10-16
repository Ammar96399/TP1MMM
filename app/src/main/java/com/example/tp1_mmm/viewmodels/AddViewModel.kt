package com.example.tp1_mmm.viewmodels

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.view.View
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tp1_mmm.db.AppDatabase
import com.example.tp1_mmm.models.Person
import com.example.tp1_mmm.vm_factory.DBArgedViewModel
import kotlinx.coroutines.launch

class AddViewModel(private val db: AppDatabase): DBArgedViewModel(db), Observable {

    // Implementation took from
    // https://developer.android.com/topic/libraries/data-binding/architecture
    // and
    // https://www.digitalocean.com/community/tutorials/android-mvvm-livedata-data-binding
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

    // Class implementation

    val firstname = MutableLiveData<String>()
    val lastname = MutableLiveData<String>()
    val birthPlace = MutableLiveData<String>()

    val person = MutableLiveData<Person>()

    private val dao = db.personDao()

    fun clear(): Unit {
        firstname.value = ""
        lastname.value = ""
        birthPlace.value = ""
    }

    fun onAddButtonClicked() {
        val pp = Person(
            firstname.value ?: "foo",
            lastname.value ?: "bar",
            birthPlace.value ?: "baz"
        )
        person.value = pp
        viewModelScope.launch {
            dao.insertAll(pp)
        }
    }
}