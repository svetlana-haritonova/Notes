package ru.haritonova.saturday10.data.db

import androidx.room.*
import androidx.room.RoomDatabase
import ru.haritonova.saturday10.presentation.model.Note

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY time DESC")
    suspend fun getAllNotes(): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)
}

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val text: String,
    val time: Long
)

fun NoteEntity.toNote() = Note(id, title, text, time)
fun Note.toEntity() = NoteEntity(id, title, text, time)