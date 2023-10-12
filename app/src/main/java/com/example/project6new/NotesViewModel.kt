package com.example.project6new

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotesViewModel(val dao: NoteDao) : ViewModel() {
    val TAG = "NotesViewModel"
    val notes = dao.getAll()
    private val _navigateToNote = MutableLiveData<Long?>()
    val navigateToNote: LiveData<Long?>
        get() = _navigateToNote
     fun addNote() {
        viewModelScope.launch {
            val note = Note()
            note.noteName = ""
            note.noteDescription = ""

            val insertNum = dao.insert(note)

            onNoteClicked(insertNum)
        }
    }


    fun deleteNote(noteId: Long) {
        dao.get(noteId).observeForever{ it ->
            it?.let{
                viewModelScope.launch {
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