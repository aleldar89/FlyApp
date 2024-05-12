package com.example.airtickets_feature

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InputCache @Inject constructor(
    context: Context
) {
    private val prefs = context.getSharedPreferences("input", Context.MODE_PRIVATE)
    private val departureLocationKey = "ID_DEPARTURE"
    private val arrivalLocationKey = "ID_ARRIVAL"

    private val _departureLocation = MutableLiveData<String?>(null)
    val departureLocation: LiveData<String?> = _departureLocation

    private val _arrivalLocation = MutableLiveData<String?>(null)
    val arrivalLocation: LiveData<String?> = _departureLocation

    init {
        prefs.getString(departureLocationKey, null)?.let {
            _departureLocation.value = it
        }
        prefs.getString(arrivalLocationKey, null)?.let {
            _arrivalLocation.value = it
        }
//            ?: prefs.edit { clear() }
    }

    @Synchronized
    fun saveDepartureLocation(location: String) {
        _departureLocation.postValue(location)
        prefs.edit {
            putString(departureLocationKey, location)
        }
    }

    @Synchronized
    fun saveArrivalLocation(location: String) {
        _arrivalLocation.postValue(location)
        prefs.edit {
            putString(arrivalLocationKey, location)
        }
    }
}