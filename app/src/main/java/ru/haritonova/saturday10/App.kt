package ru.haritonova.saturday10

import android.app.Application
import androidx.room.Room
import ru.haritonova.saturday10.data.db.NotesDatabase

class App : Application() {
  companion object {
    lateinit var db: NotesDatabase
  }

  override fun onCreate() {
    super.onCreate()
    db = Room.databaseBuilder(
      applicationContext,
      NotesDatabase::class.java,
      "notes-db"
    ).allowMainThreadQueries().build()
  }
}