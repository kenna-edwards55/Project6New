package com.example.project6new

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * ViewModel class for managing notes and navigation in the NotesFragment.
 *
 * @param dao The data access object (DAO) for note-related database operations.
 */

class NotesViewModel(val dao: NoteDao) : ViewModel() {
    val TAG = "NotesViewModel"

    /**
     * LiveData representing a list of notes.
     */
    val notes = dao.getAll()

    /**
     * LiveData to indicate navigation to a specific note.
     */
    private val _navigateToNote = MutableLiveData<Long?>()

    /**
     * LiveData representing the note to navigate to.
     */
    val navigateToNote: LiveData<Long?>
        get() = _navigateToNote

    /**
     * Adds a new, empty note to the database and navigates to the newly created note.
     */
     fun addNote() {
        viewModelScope.launch {
            Log.d(TAG, "Adding a note")
            val note = Note()
            note.noteName = ""
            note.noteDescription = ""

            val insertNum = dao.insert(note)

            onNoteClicked(insertNum)
        }
    }

    /**
     * Deletes a note with the specified note ID from the database.
     *
     * @param noteId The unique identifier of the note to be deleted.
     */
    fun deleteNote(noteId: Long) {
        Log.d(TAG, "Deleting note with ID: $noteId")
        dao.get(noteId).observeForever{ it ->
            it?.let{
                viewModelScope.launch {
                    dao.delete(it)
                }
            }
        }
    }

    /**
     * Handles a note item click event by setting the value of [navigateToNote] LiveData.
     *
     * @param noteId The unique identifier of the clicked note.
     */
    fun onNoteClicked(noteId: Long) {
        Log.d(TAG, "Note clicked with ID: $noteId")
        _navigateToNote.value = noteId
    }

    /**
     * Resets the [navigateToNote] LiveData to null after navigating to a note.
     */
    fun onNoteNavigated() {
        _navigateToNote.value = null
    }
}