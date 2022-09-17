package com.example.tp1_mmm.viewmodels

import android.os.Handler
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp1_mmm.models.Person


class SubmitViewModel: ViewModel(), Observable {

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

    val busy = MutableLiveData<Int>(8)

    val person = MutableLiveData<Person>()

    fun onLoginClicked(): Unit {
        busy.value = 0
        Handler().postDelayed(Runnable {
            val pp = Person(firstname.value ?: "foo", lastname.value ?: "bar", birthPlace.value ?: "baz")
            person.value = pp
            busy.value = 8
        }, 1500)
    }
}