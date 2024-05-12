package com.example.airtickets_data.database

import androidx.room.TypeConverter
import com.example.airtickets_data.models.FlightInfo
import com.example.airtickets_data.models.HandLuggage
import com.example.airtickets_data.models.Luggage
import com.example.airtickets_data.models.PriceModel
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
    fun fromFlightInfoToString(flightInfo: FlightInfo): String? = Gson().toJson(flightInfo)

    @TypeConverter
    fun fromStringToFlightInfo(string: String): FlightInfo =
        Gson().fromJson(string, FlightInfo::class.java)

    @TypeConverter
    fun fromLuggageToString(luggage: Luggage): String? = Gson().toJson(luggage)

    @TypeConverter
    fun fromStringToLuggage(string: String): Luggage =
        Gson().fromJson(string, Luggage::class.java)

    @TypeConverter
    fun fromHandLuggageToString(handLuggage: HandLuggage): String? = Gson().toJson(handLuggage)

    @TypeConverter
    fun fromStringToHandLuggage(string: String): HandLuggage =
        Gson().fromJson(string, HandLuggage::class.java)
}
