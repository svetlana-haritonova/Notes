package ru.haritonova.saturday10.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.haritonova.saturday10.presentation.model.Note
import ru.haritonova.saturday10.presentation.viewmodel.NotesViewModel
import ru.haritonova.saturday10.presentation.model.formatTimestamp

@Composable
fun NoteListScreen(
    viewModel: NotesViewModel,
    onAddClick: () -> Unit,
    onEditClick: (Note) -> Unit
) {
    val notes = viewModel.notes.value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(
            onClick = onAddClick,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text("Добавить заметку")
        }
        LazyColumn {
            items(notes) { note ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = note.title, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = note.text)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = formatTimestamp(note.time),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = { onEditClick(note) },
                                shape = MaterialTheme.shapes.medium
                            ) {
                                Text("Редактировать")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = { viewModel.deleteNote(note) },
                                shape = MaterialTheme.shapes.medium
                            ) {
                                Text("Удалить")
                            }
                        }
                    }
                }
            }
        }
    }
}
