package ru.haritonova.saturday10.data

import ru.haritonova.saturday10.data.db.NoteDao
import ru.haritonova.saturday10.data.db.toEntity
import ru.haritonova.saturday10.data.db.toNote
import ru.haritonova.saturday10.presentation.model.Note

class NotesRepository(private val dao: NoteDao) {
    suspend fun getAllNotes(): List<Note> =
        dao.getAllNotes().map { it.toNote() }

    suspend fun addNote(note: Note) =
        dao.insertNote(note.toEntity())

    suspend fun deleteNote(note: Note) =
        dao.deleteNote(note.toEntity())
}
