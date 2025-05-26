package ru.haritonova.saturday10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.haritonova.saturday10.presentation.model.Note
import ru.haritonova.saturday10.presentation.viewmodel.NotesViewModel
import ru.haritonova.saturday10.presentation.screen.NoteListScreen
import androidx.compose.runtime.*
import ru.haritonova.saturday10.data.NotesRepository
import ru.haritonova.saturday10.presentation.screen.NoteEditScreen

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val db = App.db
    val repository = NotesRepository(db.noteDao())
    val viewModel = NotesViewModel(repository)

    setContent {
      var screen by remember { mutableStateOf("list") }
      var editingNote by remember { mutableStateOf<Note?>(null) }

      when (screen) {
        "list" -> NoteListScreen(
          viewModel = viewModel,
          onAddClick = {
            editingNote = null
            screen = "edit"
          },
          onEditClick = {
            editingNote = it
            screen = "edit"
          }
        )

        "edit" -> NoteEditScreen(
          viewModel = viewModel,
          existingNote = editingNote,
          onDone = { screen = "list" }
        )
      }
    }
  }
}
