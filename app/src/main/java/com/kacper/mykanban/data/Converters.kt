package com.kacper.mykanban.data

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun toCalendar(value: Long): Calendar {
        return Calendar.getInstance().apply { timeInMillis = value }

    }
    @TypeConverter
    fun fromCalendar(calendar: Calendar): Long {
        return calendar.timeInMillis
    }
}