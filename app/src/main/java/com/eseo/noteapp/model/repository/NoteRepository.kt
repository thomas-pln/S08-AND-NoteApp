package com.eseo.noteapp.model.repository
import androidx.lifecycle.LiveData
import com.eseo.noteapp.model.dao.NoteDao
import com.eseo.noteapp.model.entity.Note

class NoteRepository(
    private val noteDao: NoteDao
) {

    val notes : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insertNote(note: Note){
        noteDao.insertNote(note)
    }

    suspend fun deleteNote(id : Int){
        noteDao.deleteNote(id)
    }

    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

}