package com.example.androidappno01.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.androidappno01.data.NoteEntity
import com.example.androidappno01.data.NoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: NoteRepository) : ViewModel() {
    val notes = repo.notes.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun addSample() = viewModelScope.launch {
        repo.add("Note ${System.currentTimeMillis() % 1000}", "Created from HomeViewModel + Room")
    }

    fun delete(note: NoteEntity) = viewModelScope.launch { repo.remove(note) }

    class Factory(private val repo: NoteRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            HomeViewModel(repo) as T
    }
}
