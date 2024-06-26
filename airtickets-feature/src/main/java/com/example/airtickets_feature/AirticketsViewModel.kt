package com.example.airtickets_feature

import android.content.res.Resources
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airtickets_feature.utils.dateFormat
import com.example.airtickets_feature.utils.dayOfWeekFormat
import com.example.domain.models.OfferModel
import com.example.domain.models.TicketModel
import com.example.domain.models.TicketOfferModel
import com.example.domain.usecases.GetOffersRemoteDataUseCase
import com.example.domain.usecases.GetOffersUseCase
import com.example.domain.usecases.GetTicketsOffersRemoteDataUseCase
import com.example.domain.usecases.GetTicketsOffersUseCase
import com.example.domain.usecases.GetTicketsRemoteDataUseCase
import com.example.domain.usecases.GetTicketsUseCase
import com.example.common_resources.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AirticketsViewModel @Inject constructor(
    getOffersUseCase: GetOffersUseCase,
    getTicketsOffersUseCase: GetTicketsOffersUseCase,
    getTicketsUseCase: GetTicketsUseCase,
    private val getOffersRemoteDataUseCase: GetOffersRemoteDataUseCase,
    private val getTicketsOffersRemoteDataUseCase: GetTicketsOffersRemoteDataUseCase,
    private val getTicketsRemoteDataUseCase: GetTicketsRemoteDataUseCase,
    private val inputCache: InputCache,
    private val resources: Resources
) : ViewModel() {

    val departureLocation: LiveData<String?> = inputCache.departureLocation
    val arrivalLocation: LiveData<String?> = inputCache.arrivalLocation

    val offersData: LiveData<List<OfferModel>> = getOffersUseCase.getData()
    val ticketsOffersData: LiveData<List<TicketOfferModel>> = getTicketsOffersUseCase.getData()
    val ticketsData: LiveData<List<TicketModel>> = getTicketsUseCase.getData()

    private val _departureDate = MutableLiveData(Pair("", ""))
    val departureDate: LiveData<Pair<String, String>>
        get() = _departureDate

    init {
        getRemoteData()
        getCurrentDate()
    }

    private fun getRemoteData() = doRequest {
        getOffersRemoteDataUseCase.getData()
        getTicketsOffersRemoteDataUseCase.getData()
        getTicketsRemoteDataUseCase.getData()
    }

    fun saveDepartureLocation(location: String) = doRequest {
        inputCache.saveDepartureLocation(location)
    }

    fun saveArrivalLocation(location: String) = doRequest {
        inputCache.saveArrivalLocation(location)
    }

    fun saveDepartureDate(date: Calendar) {
        _departureDate.value = Pair(
            dateFormat().format(date.time),
            resources.getString(R.string.day_of_week, dayOfWeekFormat().format(date.time))
        )
    }

    private fun getCurrentDate() {
        val currentDate = Calendar.getInstance().time
        _departureDate.value = Pair(
            dateFormat().format(currentDate),
            resources.getString(R.string.day_of_week, dayOfWeekFormat().format(currentDate))
        )
    }

    private fun doRequest(
        request: suspend () -> Unit
    ) = try {
        viewModelScope.launch(Dispatchers.IO) { request() }
    } catch (e: Exception) {
        throw e
    }

    fun removeObservers(lifecycleOwner: LifecycleOwner) =
        arrayOf(
            offersData,
            ticketsOffersData,
            ticketsData,
            departureDate,
            departureLocation,
            arrivalLocation
        ).forEach { it.removeObservers(lifecycleOwner) }
}