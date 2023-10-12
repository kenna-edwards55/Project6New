package com.example.project6new

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * ViewModel class for editing a note.
 *
 * @param noteId The unique identifier of the note being edited.
 * @param dao The data access object (DAO) for note-related database operations.
 */
class EditNoteViewModel(noteId: Long, val dao: NoteDao) : ViewModel() {
    val TAG = "EditNoteViewModel"

    /**
     * The note being edited.
     */
    val note = dao.get(noteId)

    /**
     * LiveData to indicate whether to navigate to the note list.
     */
    private val _navigateToList = MutableLiveData<Boolean>(false)

    /**
     * LiveData representing the navigation state to the note list.
     */
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    /**
     * Updates the note in the database and triggers navigation to the note list.
     */
    fun updateNote() {
        viewModelScope.launch {
            Log.d(TAG, "Updating the note.")

            dao.update(note.value!!)
            _navigateToList.value = true
        }
    }

    /**
     * Resets the navigation flag to prevent multiple navigations to the note list.
     */
    fun onNavigatedToList() {
        _navigateToList.value = false
    }
}


