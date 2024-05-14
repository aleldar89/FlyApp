package com.example.airtickets_data.database

import androidx.room.TypeConverter
import com.example.domain.models.FlightInfoModel
import com.example.domain.models.HandLuggageModel
import com.example.domain.models.LuggageModel
import com.example.domain.models.PriceModel
import com.google.gson.Gson

class Converter {

    @TypeConverter
    fun fromListToString(list: List<String>): String? = Gson().toJson(list)

    @TypeConverter
    fun fromStringToList(string: String): List<String> =
        Gson().fromJson(string, Array<String>::class.java).toList()

    @TypeConverter
    fun fromPriceModelToInt(price: PriceModel): Int = price.value

    @TypeConverter
    fun fromIntToPriceModel(value: Int): PriceModel = PriceModel(value)

    @TypeConverter
    fun fromFlightInfoToString(flightInfo: FlightInfoModel): String? = Gson().toJson(flightInfo)

    @TypeConverter
    fun fromStringToFlightInfo(string: String): FlightInfoModel =
        Gson().fromJson(string, FlightInfoModel::class.java)

    @TypeConverter
    fun fromLuggageToString(luggage: LuggageModel): String = Gson().toJson(luggage)

    @TypeConverter
    fun fromStringToLuggage(string: String): LuggageModel =
        Gson().fromJson(string, LuggageModel::class.java)

    @TypeConverter
    fun fromHandLuggageToString(handLuggage: HandLuggageModel): String = Gson().toJson(handLuggage)

    @TypeConverter
    fun fromStringToHandLuggage(string: String): HandLuggageModel =
        Gson().fromJson(string, HandLuggageModel::class.java)
}
