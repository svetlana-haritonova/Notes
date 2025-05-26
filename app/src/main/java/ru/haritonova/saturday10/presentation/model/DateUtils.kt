package ru.haritonova.saturday10.presentation.model

import java.text.SimpleDateFormat
import java.util.*

fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
