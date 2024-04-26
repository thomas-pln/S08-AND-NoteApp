package com.eseo.noteapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eseo.noteapp.model.dao.NoteDao
import com.eseo.noteapp.model.entity.Note


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao() : NoteDao

    companion object{

        @Volatile
        private var NOTE_INSTANCE : NoteDatabase? = null

        fun getNoteDatabase(context: Context) : NoteDatabase {
            if (NOTE_INSTANCE == null){
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