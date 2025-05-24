package ru.haritonova.saturday10.presentation.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import ru.haritonova.saturday10.data.NotesRepository
import ru.haritonova.saturday10.data.db.NoteEntity
import java.util.UUID
import androidx.compose.runtime.setValue
import ru.haritonova.saturday10.presentation.screen.LoginScreen
import ru.haritonova.saturday10.presentation.screen.NoteFormScreen
import ru.haritonova.saturday10.presentation.screen.NotesListScreen

@Composable
fun NotesApp(
    authViewModel: AuthViewModel,
    notesViewModel: NotesViewModel,
    repository: NotesRepository,
    isInitiallyLoggedIn: Boolean
) {
    var loggedIn by remember { mutableStateOf(isInitiallyLoggedIn) }
    var editingNote by remember { mutableStateOf<NoteEntity?>(null) }

    when {
        !loggedIn -> LoginScreen(
            viewModel = authViewModel,
            onLoginSuccess = { token ->
                repository.setToken(token)
                loggedIn = true
            }
        )

        editingNote != null -> NoteFormScreen(
            initialTitle = editingNote!!.title,
            initialText = editingNote!!.text,
            onSave = { title, text ->
                val updated = editingNote!!.copy(title = title, text = text)
                if (updated.id.isBlank()) {
                    notesViewModel.add(updated.copy(id = UUID.randomUUID().toString()))
                } else {
                    notesViewModel.update(updated)
                }
                editingNote = null
            },
            onCancel = { editingNote = null }
        )

        else -> NotesListScreen(
            viewModel = notesViewModel,
            onAdd = { editingNote = NoteEntity("", "", "") },
            onEdit = { note -> editingNote = note },
            onDelete = { note -> notesViewModel.delete(note) }
        )
    }
}
