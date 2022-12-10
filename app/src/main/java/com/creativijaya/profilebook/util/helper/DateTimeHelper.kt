package com.creativijaya.profilebook.util.helper

import com.creativijaya.profilebook.util.ext.callOrNull
import com.creativijaya.profilebook.util.ext.orZero
import java.text.SimpleDateFormat
import java.util.*

object DateTimeHelper {

    const val API_DATE_FORMAT = "yyyy-MM-dd"
    const val API_DATE_FORMAT_V2 = "dd/MM/yyyy"
    const val API_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val API_TZ_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.000'Z'"
    const val DISPLAY_SIMPLE_DATE_FORMAT = "dd MMMM yyyy"
    const val DISPLAY_DATE_FORMAT = "EEEE, dd MMMM yyyy"
    const val DISPLAY_FULL_DATE_FORMAT = "dd MMMM yyyy, HH:mm"
    const val DISPLAY_INSPECTION_FULL_DATE_FORMAT = "dd - MM - yyyy | HH:mm"
    const val DISPLAY_SHORT_DATE_FORMAT = "dd MMM yyyy, HH:mm"
    const val DAY_ONLY = "dd"
    const val MONTH_YEAR_ONLY = "MMM/yy"

    private val locale by lazy {
        Locale("id", "ID")
    }

    fun getDate(
        dateTime: String,
        fromFormat: String = API_DATE_FORMAT
    ): Date? = callOrNull {
        SimpleDateFormat(fromFormat, locale).parse(dateTime)
    }

    fun getTime(
        dateTime: String,
        fromFormat: String = API_DATE_FORMAT
    ): Long? = callOrNull {
        SimpleDateFormat(fromFormat, locale).parse(dateTime)?.time
    }

    fun translateTime(
        time: Long?,
        expectedFormat: String = DISPLAY_SIMPLE_DATE_FORMAT
    ): String? = callOrNull {
        SimpleDateFormat(expectedFormat, locale).format(Date(time.orZero()))
    }

    fun convertTimeStr(
        time: String,
        fromFormat: String,
        expectedFormat: String
    ): String? = callOrNull {
        translateTime(getTime(time, fromFormat), expectedFormat)
    }

}
