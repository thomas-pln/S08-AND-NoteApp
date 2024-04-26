package com.eseo.noteapp

import android.app.Application
import com.eseo.noteapp.model.NoteDatabase
import com.eseo.noteapp.model.repository.NoteRepository

class NoteApplication : Application() {

    val database by lazy { NoteDatabase.getNoteDatabase(this) }
    val repository by lazy { NoteRepository(database.getNoteDao()) }

}