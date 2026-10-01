package com.example.androidappno01.data

import kotlinx.coroutines.flow.Flow

class NoteRepository(private val dao: NoteDao) {
    val notes: Flow<List<NoteEntity>> = dao.observeAll()
    suspend fun get(id: Long) = dao.getById(id)
    suspend fun add(title: String, content: String) =
        dao.insert(NoteEntity(title = title, content = content))
    suspend fun remove(note: NoteEntity) = dao.delete(note)
}
