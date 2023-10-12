package com.example.project6new

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EditNoteViewModel(noteId: Long, val dao: NoteDao) : ViewModel() {
    val TAG = "EditNoteViewModel"
    val note = dao.get(noteId)
    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList
    fun updateNote() {
        viewModelScope.launch {
            Log.d("Edit", note.value!!.noteName)
            dao.update(note.value!!)
            _navigateToList.value = true
        }
    }



    fun onNavigatedToList() {
        _navigateToList.value = false
    }
}


