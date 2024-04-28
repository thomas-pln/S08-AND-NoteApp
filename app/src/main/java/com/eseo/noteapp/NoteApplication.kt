package com.eseo.noteapp

import android.app.Application
import com.eseo.noteapp.model.NoteDatabase
import com.eseo.noteapp.model.repository.NoteRepository

/**
 * Custom application class that provides access to the database and repository.
 *
 * @property database The database that provides access to the note data.
 * @property repository The repository that provides access to the note data.
 */
class NoteApplication : Application() {

    val database by lazy { NoteDatabase.getNoteDatabase(this) }
    val repository by lazy { NoteRepository(database.getNoteDao()) }

}