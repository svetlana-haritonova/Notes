package ru.haritonova.saturday10.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.haritonova.saturday10.presentation.model.Note
import ru.haritonova.saturday10.presentation.viewmodel.NotesViewModel

@Composable
fun NoteEditScreen(
    viewModel: NotesViewModel,
    existingNote: Note?,
    onDone: () -> Unit
) {
    var title by remember { mutableStateOf(existingNote?.title ?: "") }
    var content by remember { mutableStateOf(existingNote?.text ?: "") }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Заголовок") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Содержание") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = { onDone() },
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Отменить")
            }

            Button(
                onClick = {
                    val note = Note(
                        id = existingNote?.id ?: 0,
                        title = title,
                        text = content,
                        time = System.currentTimeMillis()
                    )
                    if (existingNote == null) {
                        viewModel.addNote(note)
                    } else {
                        viewModel.updateNote(note)
                    }
                    onDone()
                },
                shape = MaterialTheme.shapes.medium
            ) {
                Text(if (existingNote == null) "Сохранить" else "Обновить")
            }
        }
    }
}
