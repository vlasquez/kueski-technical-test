package com.technicaltest.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun transformTodayDate(): String {
    val calendar = Calendar.getInstance()
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.format(calendar.time)
}