package com.example.project6new

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory for creating an instance of the EditNoteViewModel.
 *
 * @param noteId The unique identifier of the note being edited.
 * @param dao The data access object (DAO) for note-related database operations.
 */
class EditNoteViewModelFactory(private val noteId: Long,
                               private val dao: NoteDao)
    : ViewModelProvider.Factory {

    /**
     * Creates and returns an instance of the EditNoteViewModel based on the specified model class.
     *
     * @param modelClass The class of the ViewModel to be created.
     * @return A new instance of the EditNoteViewModel.
     * @throws IllegalArgumentException If the provided modelClass is not EditNoteViewModel.
     */
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditNoteViewModel::class.java)) {
            return EditNoteViewModel(noteId, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}