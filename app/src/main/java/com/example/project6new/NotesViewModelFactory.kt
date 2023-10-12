package com.example.project6new

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory for creating an instance of the NotesViewModel.
 *
 * @param dao The data access object (DAO) for note-related database operations.
 */
class NotesViewModelFactory(private val dao: NoteDao)
    : ViewModelProvider.Factory {

    /**
     * Creates and returns an instance of the NotesViewModel based on the specified model class.
     *
     * @param modelClass The class of the ViewModel to be created.
     * @return A new instance of the NotesViewModel.
     * @throws IllegalArgumentException If the provided modelClass is not NotesViewModel.
     */

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}