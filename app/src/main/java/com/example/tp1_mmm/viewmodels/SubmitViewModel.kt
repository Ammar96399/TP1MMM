package com.example.tp1_mmm.viewmodels

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.view.View
import android.widget.CalendarView
import androidx.databinding.BindingAdapter
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.test.core.app.launchActivity
import com.example.tp1_mmm.models.Person
import com.example.tp1_mmm.views.MainActivity
import java.util.*

data class LongWrapper(var value: Long?)

@BindingAdapter("android:date")
fun setCalendar(view: CalendarView, value: LongWrapper?) {
    view.let {
        value?.value = view.date
    }
}

class SubmitViewModel(private val app: Application): AndroidViewModel(app), Observable {

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
    val birthRegion = MutableLiveData<String>()

    var calendar = MutableLiveData<LongWrapper>()

    private val busy = MutableLiveData<Int>(8)
    val setPasswordEnabled = MutableLiveData(View.INVISIBLE)
    val validateButtonName = MutableLiveData<String>("Confirm")

    val person = MutableLiveData<Person>()

    fun enablePassword(): Unit {
        setPasswordEnabled.value = View.VISIBLE
        validateButtonName.value = "Validate"
    }

    fun disablePassword(): Unit {
        setPasswordEnabled.value = View.INVISIBLE
        validateButtonName.value = "Confirm"
    }

    fun clear(): Unit {
        firstname.value = ""
        lastname.value = ""
        birthPlace.value = ""
        birthRegion.value = ""
        disablePassword()
    }

    fun onSubmitClicked(): Unit {
        if (setPasswordEnabled.value == View.INVISIBLE) {
            enablePassword()
        } else {
            busy.value = 0
            Handler().postDelayed({
                val pp = Person(
                    firstname.value ?: "foo",
                    lastname.value ?: "bar",
                    birthPlace.value ?: "baz"
                )
                person.value = pp
                busy.value = 8
                disablePassword()
            }, 1500)
        }
    }

    fun onIntentClicked(): Unit {

        Intent(Intent.ACTION_VIEW, Uri.parse("https://fr.wikipedia.org")).let {
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            app.startActivity(it)
        }
    }
}