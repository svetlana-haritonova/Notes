package ru.haritonova.saturday10.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.haritonova.saturday10.data.db.NoteEntity
import androidx.compose.foundation.lazy.items
import ru.haritonova.saturday10.presentation.model.NotesViewModel


@Composable
fun NotesListScreen(
    viewModel: NotesViewModel,
    onAdd: () -> Unit,
    onEdit: (NoteEntity) -> Unit,
    onDelete: (NoteEntity) -> Unit
) {
    val notes = viewModel.notes
    LaunchedEffect(Unit) { viewModel.loadNotes() }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { onAdd() }) { Text("Добавить") }
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(notes) { note ->
                Card(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(note.title, style = MaterialTheme.typography.titleLarge)
                        Text(note.text)
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            TextButton(onClick = { onEdit(note) }) { Text("Редактировать") }
                            TextButton(onClick = { onDelete(note) }) { Text("Удалить") }
                        }
                    }
                }
            }
        }
        Button(onClick = { viewModel.sync() }) { Text("Синхронизировать") }
    }
}