package ru.haritonova.saturday10.data

import ru.haritonova.saturday10.data.api.NoteDTO
import ru.haritonova.saturday10.data.api.NotesApi
import ru.haritonova.saturday10.data.db.NoteDao
import ru.haritonova.saturday10.data.db.NoteEntity

class NotesRepository(
    private val api: NotesApi,
    private val dao: NoteDao,
    private var token: String = ""
) {
    private val _notes = mutableListOf<NoteEntity>()
    fun getNotes(): List<NoteEntity> = _notes

    fun setToken(newToken: String) {
        token = newToken
    }

    suspend fun loadLocalNotes() {
        _notes.clear()
        _notes.addAll(dao.getAll())
    }

    suspend fun sync() {
        val remoteNotes = api.getNotes("Bearer $token")
        dao.insertAll(remoteNotes.map { NoteEntity(it.id, it.title, it.text) })
        loadLocalNotes()
    }

    suspend fun addNote(note: NoteEntity) {
        dao.insert(note)
        api.createNote("Bearer $token", NoteDTO(title = note.title, text = note.text))
        loadLocalNotes()
    }

    suspend fun updateNote(note: NoteEntity) {
        dao.insert(note)
        api.updateNote("Bearer $token", note.id, NoteDTO(title = note.title, text = note.text))
        loadLocalNotes()
    }

    suspend fun deleteNote(note: NoteEntity) {
        dao.delete(note)
        api.deleteNote("Bearer $token", note.id)
        loadLocalNotes()
    }
}