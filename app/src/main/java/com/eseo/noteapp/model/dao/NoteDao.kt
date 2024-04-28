package com.eseo.noteapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.eseo.noteapp.model.entity.Note

/**
 * NoteDao is an interface that defines the methods for database operations.
 * It uses Room persistence library annotations to define the methods for insert, update, delete and query operations.
 */
@Dao
interface NoteDao {

    /**
     * Fetches all notes from the database.
     * @return LiveData<List<Note>>: List of all notes wrapped in LiveData.
     */
    @Query("SELECT * FROM note")
    fun getAllNotes() : LiveData<List<Note>>

    /**
     * Inserts a note into the database. If a note with the same ID already exists, it gets replaced.
     * @param note: The note to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    /**
     * Updates a note in the database. If a note with the same ID already exists, it gets replaced.
     * @param note: The note to be updated.
     */
    @Update(entity = Note::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    /**
     * Deletes a note from the database.
     * @param id: The id of the note to be deleted.
     */
    @Query("DELETE FROM note WHERE id = :id")
    suspend fun deleteNote(id : Int)
}