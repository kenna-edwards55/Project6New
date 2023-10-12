package com.example.project6new

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing a Note entity for storage in a Room database.
 *
 * @param noteId The unique identifier for the note (auto-generated).
 * @param noteName The name or title of the note.
 * @param noteDescription The description or content of the note.
 * @param noteDone A boolean flag indicating whether the note is marked as done or not (not in use).
 */
@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0L,
    @ColumnInfo(name = "note_name")
    var noteName: String = "",
    @ColumnInfo(name = "note_description")
    var noteDescription: String = "",
    @ColumnInfo(name = "note_done")
    var noteDone: Boolean = false // Not in use, can be used for marking notes as done.
)
