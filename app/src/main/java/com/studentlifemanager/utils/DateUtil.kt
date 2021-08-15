package com.studentlifemanager.utils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun getTodayDate(): String? {
        return SimpleDateFormat("dd MMM yyyy").format(Calendar.getInstance().time)
    }

    fun formatDateFromString(
        inputFormat: String?,
        outputFormat: String?,
        inputDate: String?
    ): String? {
        var parsed: Date? = null
        var outputDate= ""
        val date_input = SimpleDateFormat(inputFormat, Locale.getDefault())
        val date_output = SimpleDateFormat(outputFormat, Locale.getDefault())
        try {
            parsed = date_input.parse(inputDate)
            outputDate = date_output.format(parsed)
        } catch (e: ParseException) {
            return getTodayDate()
        }
        return outputDate
    }
}