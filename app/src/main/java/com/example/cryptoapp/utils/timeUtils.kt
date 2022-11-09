package com.example.cryptoapp.utils

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import java.sql.Timestamp
import java.util.*


fun convertTimestampToTime(timestamp: Int?):String{
    if (timestamp == null) return ""
    val stamp = Timestamp((timestamp * 1000).toLong())
    val date = Date(stamp.time)
    val pattern = "HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}