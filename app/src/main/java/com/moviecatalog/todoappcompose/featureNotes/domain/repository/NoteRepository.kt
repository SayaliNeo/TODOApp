package com.moviecatalog.todoappcompose.featureNotes.domain.repository

import com.moviecatalog.todoappcompose.featureNotes.domain.model.Notes
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAllNotes(): Flow<List<Notes>>
    suspend fun getNotesById(id: Int): Notes

    suspend fun addNotes(notes: Notes)

    suspend fun deleteNotes(notes: Notes)


}