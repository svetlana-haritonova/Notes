package ru.haritonova.saturday10.presentation.model

data class Note(
    val id: Int = 0,
    val title: String,
    val text: String,
    val time: Long = System.currentTimeMillis()
)
