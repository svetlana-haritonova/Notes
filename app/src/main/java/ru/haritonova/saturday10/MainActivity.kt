package ru.haritonova.saturday10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.haritonova.saturday10.data.NotesRepository
import ru.haritonova.saturday10.data.api.NotesApi
import ru.haritonova.saturday10.presentation.model.AuthViewModel
import ru.haritonova.saturday10.presentation.model.NotesApp
import ru.haritonova.saturday10.presentation.model.NotesViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
    val savedToken = prefs.getString("token", null)

    val api = Retrofit.Builder()
      .baseUrl("https://notes-api.bel4.com/api/v1/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(NotesApi::class.java)

    val repository = NotesRepository(api, App.db.noteDao())
    val authViewModel = AuthViewModel(api, prefs)
    val notesViewModel = NotesViewModel(repository)

    if (savedToken != null) {
      repository.setToken(savedToken)
    }

    setContent {
      NotesApp(authViewModel, notesViewModel, repository, isInitiallyLoggedIn = savedToken != null)
    }
  }
}
