package com.eseo.noteapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.model.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the data related to notes.
 *
 * @property noteRepository The repository that provides access to the note data.
 * @property notesLiveData LiveData object that holds the list of notes.
 */
class NoteViewModel(
    private val noteRepository : NoteRepository
) : ViewModel() {

    val notesLiveData : LiveData<List<Note>> = noteRepository.notes

    /**
     * Inserts a new note into the database.
     *
     * @param note The note to be inserted.
     */
    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.insertNote(note)
    }

    /**
     * Deletes a note from the database.
     *
     * @param id The id of the note to be deleted.
     */
    fun deleteNote(id : Int) = viewModelScope.launch(Dispatchers.IO){
        noteRepository.deleteNote(id)
    }

    /**
     * Updates a note in the database.
     *
     * @param note The note with updated information.
     */
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.updateNote(note)
    }
}