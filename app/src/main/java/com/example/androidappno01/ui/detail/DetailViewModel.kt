package com.example.androidappno01.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.androidappno01.data.NoteEntity
import com.example.androidappno01.data.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repo: NoteRepository, private val noteId: Long) : ViewModel() {
    private val _note = MutableStateFlow<NoteEntity?>(null)
    val note: StateFlow<NoteEntity?> = _note

    init {
        viewModelScope.launch { _note.value = repo.get(noteId) }
    }

    class Factory(private val repo: NoteRepository, private val noteId: Long) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            DetailViewModel(repo, noteId) as T
    }
}
