package com.e.porsche.managers

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {


    fun rawToDate(date: Date?, year: Int, monthOfYear: Int, dayOfMonth: Int): Date {
        val calendar = Calendar.getInstance()
        date?.let { calendar.time = it }
        calendar.set(year, monthOfYear, dayOfMonth)
        return calendar.time
    }

    fun rawToTime(date: Date?, hour: Int, minute: Int): Date {
        val calendar = Calendar.getInstance()
        date?.let { calendar.time = it }
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        return calendar.time
    }

    fun prepareDateForSelling(date: String): Date {
        val format = SimpleDateFormat("MM/dd/yy", Locale.ITALIAN)
        return format.parse(date)
    }

    fun prepareDateForSelling(date: Date): String {
        val format = SimpleDateFormat("MM/dd/yy", Locale.ITALIAN)
        return format.format(date).capitalize()
    }

    fun prepareTimeForSelling(date: String): Date {
        val format = SimpleDateFormat("MM/dd/yy", Locale.ITALIAN)
        return format.parse(date)
    }

    fun prepareTimeForSelling(date: Date): String {
        val format = SimpleDateFormat("HH:mm", Locale.ITALIAN)
        return format.format(date).capitalize()
    }
}
