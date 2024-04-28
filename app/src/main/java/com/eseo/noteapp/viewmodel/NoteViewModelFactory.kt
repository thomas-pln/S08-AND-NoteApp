package com.eseo.noteapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eseo.noteapp.model.repository.NoteRepository

/**
 * Factory for creating a NoteViewModel with a constructor that takes a NoteRepository.
 *
 * @property noteRepository The repository that provides access to the note data.
 */
class NoteViewModelFactory(
    private val noteRepository : NoteRepository
) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of the given Class.
     *
     * @param modelClass a Class whose instance is requested
     * @return a newly created ViewModel
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(noteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}