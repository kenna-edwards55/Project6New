package com.example.project6new

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EditNoteViewModelFactory(private val noteId: Long,
                               private val dao: NoteDao)
    : ViewModelProvider.Factory {

      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditNoteViewModel::class.java)) {
            return EditNoteViewModel(noteId, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}