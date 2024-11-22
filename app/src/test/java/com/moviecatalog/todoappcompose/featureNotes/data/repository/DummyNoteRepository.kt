package com.moviecatalog.todoappcompose.featureNotes.data.repository

import com.moviecatalog.todoappcompose.featureNotes.data.data_source.NoteDao
import com.moviecatalog.todoappcompose.featureNotes.domain.model.Notes
import com.moviecatalog.todoappcompose.featureNotes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DummyNoteRepository:NoteRepository {
    private val noteList = mutableListOf<Notes>()

    override fun getAllNotes(): Flow<List<Notes>> {
        return flow{emit(noteList)}
    }

    override suspend fun getNotesById(id: Int): Notes {
        return noteList.find { it.id == id }!!
    }

    override suspend fun addNotes(notes: Notes) {
         noteList.add(notes)
    }

    override suspend fun deleteNotes(notes: Notes) {
         noteList.remove(notes)
    }

}