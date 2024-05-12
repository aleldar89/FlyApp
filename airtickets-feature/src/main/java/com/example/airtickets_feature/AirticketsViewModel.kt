package com.example.airtickets_feature

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airtickets_data.models.OfferModel
import com.example.airtickets_data.models.TicketModel
import com.example.airtickets_data.models.TicketOfferModel
import com.example.airtickets_data.usecases.GetOffersRemoteDataUseCase
import com.example.airtickets_data.usecases.GetOffersUseCase
import com.example.airtickets_data.usecases.GetTicketsOffersRemoteDataUseCase
import com.example.airtickets_data.usecases.GetTicketsOffersUseCase
import com.example.airtickets_data.usecases.GetTicketsRemoteDataUseCase
import com.example.airtickets_data.usecases.GetTicketsUseCase
import com.example.common_resources.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
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

    private fun getCurrentDate() {
        val currentDate = Calendar.getInstance().time
        val date =
            SimpleDateFormat("dd MMM", Locale.getDefault()).format(currentDate)
        val dayOfWeek =
            SimpleDateFormat("EE", Locale.getDefault()).format(currentDate)

        _departureDate.value = Pair(
            date,
            resources.getString(R.string.day_of_week, dayOfWeek)
        )
    }

    private fun doRequest(
        request: suspend () -> Unit
    ) = try {
        viewModelScope.launch(Dispatchers.IO) {
            request()
        }
    } catch (e: Exception) {
        throw e
    }
}