package com.eseo.noteapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eseo.noteapp.model.dao.NoteDao
import com.eseo.noteapp.model.entity.Note


/**
 * Represents the database for the application.
 *
 * @property getNoteDao Provides access to the NoteDao for database operations.
 */
@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    /**
     * Provides access to the NoteDao for database operations.
     *
     * @return NoteDao The DAO object that has access to the Note database.
     */
    abstract fun getNoteDao(): NoteDao

    companion object {

        /**
         * Singleton instance of the NoteDatabase.
         */
        @Volatile
        private var NOTE_INSTANCE: NoteDatabase? = null

        /**
         * Returns the singleton instance of the NoteDatabase.
         *
         * @param context The context of the caller.
         * @return NoteDatabase The singleton instance of the NoteDatabase.
         */
        fun getNoteDatabase(context: Context): NoteDatabase {
            if (NOTE_INSTANCE == null) {
                val instance = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    "noteDB"
                ).build()
                NOTE_INSTANCE = instance
            }
            return NOTE_INSTANCE!!
        }
    }
}