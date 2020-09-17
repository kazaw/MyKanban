package com.kacper.mykanban

import com.kacper.mykanban.data.Converters
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*
import java.util.Calendar.*

class ConvertersTest {

    private val calendar: Calendar = getInstance().apply {
        set(YEAR, 2000)
        set(MONTH, 10)
        set(DAY_OF_MONTH,10)
    }

    @Test
    fun fromCalendar(){
        assertEquals(calendar.timeInMillis, Converters().fromCalendar(calendar))
    }

    @Test
    fun toCalendar(){
        assertEquals(Converters().toCalendar(calendar.timeInMillis), calendar)
    }
}