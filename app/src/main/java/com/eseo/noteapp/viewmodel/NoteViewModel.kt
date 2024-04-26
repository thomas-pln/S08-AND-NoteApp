package com.eseo.noteapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.model.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteRepository : NoteRepository
) : ViewModel() {

    val notesLiveData : LiveData<List<Note>> = noteRepository.notes

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.insertNote(note)
    }

    fun deleteNote(id : Int) = viewModelScope.launch(Dispatchers.IO){
        noteRepository.deleteNote(id)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.updateNote(note)
    }

}