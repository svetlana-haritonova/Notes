package ru.haritonova.saturday10.presentation.model

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.haritonova.saturday10.data.NotesRepository
import ru.haritonova.saturday10.data.db.NoteEntity

class NotesViewModel(private val repository: NotesRepository) : ViewModel() {
    private val _notes = mutableStateListOf<NoteEntity>()
    val notes: List<NoteEntity> get() = _notes

    fun loadNotes() = viewModelScope.launch {
        repository.loadLocalNotes()
        _notes.clear()
        _notes.addAll(repository.getNotes())
    }

    fun sync() = viewModelScope.launch {
        try {
            repository.sync()
            _notes.clear()
            _notes.addAll(repository.getNotes())
        } catch (e: Exception) {
            Log.e("NotesViewModel", "Ошибка синхронизации", e)
        }
    }

    fun add(note: NoteEntity) = viewModelScope.launch {
        repository.addNote(note)
        _notes.clear()
        _notes.addAll(repository.getNotes())
    }

    fun update(note: NoteEntity) = viewModelScope.launch {
        repository.updateNote(note)
        _notes.clear()
        _notes.addAll(repository.getNotes())
    }

    fun delete(note: NoteEntity) = viewModelScope.launch {
        repository.deleteNote(note)
        _notes.clear()
        _notes.addAll(repository.getNotes())
    }
}