package ru.haritonova.saturday10.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import ru.haritonova.saturday10.data.NotesRepository
import ru.haritonova.saturday10.presentation.model.Note

class NotesViewModel(private val repository: NotesRepository) : ViewModel() {
    private val _notes = mutableStateOf<List<Note>>(emptyList())
    val notes: State<List<Note>> = _notes

    init {
        loadNotes()
    }

    fun loadNotes() {
        viewModelScope.launch {
            _notes.value = repository.getAllNotes()
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.addNote(note)
            loadNotes()
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.addNote(note)
            loadNotes()
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
            loadNotes()
        }
    }
}