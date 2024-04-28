package com.eseo.noteapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Represents a note in the application.
 *
 * @property id The unique ID of the note.
 * @property title The title of the note.
 * @property text The text content of the note.
 */
@Entity(tableName = "note")
data class Note(
    /**
     * The unique ID of the note. This is the primary key and is auto-generated.
     */
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    /**
     * The title of the note. Stored in the 'title' column in the database.
     */
    @ColumnInfo(name = "title")
    var title: String,

    /**
     * The text content of the note. Stored in the 'text' column in the database.
     */
    @ColumnInfo(name = "text")
    var text: String
) : Serializable