package com.eseo.noteapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.eseo.noteapp.model.entity.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM note")
    fun getAllNotes() : LiveData<List<Note>>

    @Query("DELETE FROM note WHERE id = :id")
    suspend fun deleteNote(id : Int)

    @Update(
        entity = Note::class,
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun updateNote(note: Note)

}