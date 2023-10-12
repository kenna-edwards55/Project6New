package com.example.project6new

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Data Access Object (DAO) interface for performing CRUD (Create, Read, Update, Delete) operations on Note entities.
 */
@Dao
interface NoteDao {
    /**
     * Inserts a new note into the database.
     *
     * @param note The Note object to be inserted.
     * @return The unique identifier of the inserted note.
     */
    @Insert
    suspend fun insert(note: Note) :Long

    /**
     * Updates an existing note in the database.
     *
     * @param note The Note object to be updated.
     */
    @Update
    suspend fun update(note: Note)

    /**
     * Deletes a note from the database.
     *
     * @param note The Note object to be deleted.
     */
    @Delete
    suspend fun delete(note: Note)

    /**
     * Retrieves a specific note by its unique identifier.
     *
     * @param key The unique identifier of the note to retrieve.
     * @return LiveData containing the requested Note object.
     */
    @Query("SELECT * FROM note_table WHERE noteId = :key")
    fun get(key: Long): LiveData<Note>

    /**
     * Retrieves all notes from the database, ordered by their unique identifiers in descending order.
     *
     * @return LiveData containing a list of all Note objects.
     */
    @Query("SELECT * FROM note_table ORDER BY noteId DESC")
    fun getAll(): LiveData<List<Note>>
}