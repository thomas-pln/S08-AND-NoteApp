package com.eseo.noteapp.model.repository

import androidx.lifecycle.LiveData
import com.eseo.noteapp.model.dao.NoteDao
import com.eseo.noteapp.model.entity.Note

/**
 * Repository module for handling data operations.
 *
 * @property noteDao The DAO object that has access to the Note database.
 * @property notes LiveData object that holds a list of all notes.
 */
class NoteRepository(
    private val noteDao: NoteDao
) {
    /**
     * LiveData object that holds a list of all notes.
     */
    val notes: LiveData<List<Note>> = noteDao.getAllNotes()

    /**
     * Insert a note into the database.
     *
     * @param note The note to be inserted.
     */
    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    /**
     * Update a note in the database.
     *
     * @param note The note to be updated.
     */
    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    /**
     * Delete a note from the database.
     *
     * @param id The id of the note to be deleted.
     */
    suspend fun deleteNote(id: Int) {
        noteDao.deleteNote(id)
    }
}