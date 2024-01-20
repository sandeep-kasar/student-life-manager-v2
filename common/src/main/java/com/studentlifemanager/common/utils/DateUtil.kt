package com.studentlifemanager.common.utils

import com.studentlifemanager.common.utils.Constant.DD_MMM_YYYY
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * This is the object which has different function to handle date related operations
 */
object DateUtil {

    /**
     * This function calculates the today's date
     */
    fun getTodayDate(): String {
        return SimpleDateFormat(DD_MMM_YYYY).format(Calendar.getInstance().time)
    }

    /**
     * This function converts date from one format to other
     *
     * @param inputDate input date format
     * @param outputFormat output date format
     * @param inputDate input date which is need to convert
     */
    fun formatDateFromString(
        inputFormat: String?,
        outputFormat: String?,
        inputDate: String?
    ): String {
        var parsed: Date? = null
        var outputDate = ""
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