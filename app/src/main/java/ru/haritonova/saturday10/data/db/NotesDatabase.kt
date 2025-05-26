package ru.haritonova.saturday10.data.db

import android.content.Context
import androidx.room.*
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.haritonova.saturday10.presentation.model.Note

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "note_database"
                ).build()
            }
            return INSTANCE!!
        }
    }
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