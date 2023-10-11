package com.example.project6new

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotesViewModel(val dao: NoteDao) : ViewModel() {
    var newNoteName = ""
    var newNoteDescription= ""
    val notes = dao.getAll()
    private val _navigateToNote = MutableLiveData<Long?>()
    val navigateToNote: LiveData<Long?>
        get() = _navigateToNote
    fun addNote() {
        viewModelScope.launch {
            val note = Note()
            note.noteName = newNoteName
            note.noteDescription= newNoteDescription
            dao.insert(note)
        }
    }

    fun deleteNote(noteId: Long) {
        dao.get(noteId).observeForever{ it ->
            it?.let{
                viewModelScope.launch {
                    Log.d("In the view model", "Trying to delete" + it.noteId)
                    dao.delete(it)
                }
            }
        }

    }

    fun onNoteClicked(noteId: Long) {
        _navigateToNote.value = noteId
    }

    fun onNoteNavigated() {
        _navigateToNote.value = null
    }
}