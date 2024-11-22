package com.moviecatalog.todoappcompose.featureNotes.data.repository

import com.moviecatalog.todoappcompose.featureNotes.data.data_source.NoteDao
import com.moviecatalog.todoappcompose.featureNotes.domain.model.Notes
import com.moviecatalog.todoappcompose.featureNotes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao: NoteDao):NoteRepository {

    override fun getAllNotes(): Flow<List<Notes>> {
        return noteDao.getAllNotes()
    }

    override suspend fun getNotesById(id: Int): Notes  = noteDao.getNoteById(id)

    override suspend fun  addNotes(notes: Notes) = noteDao.insertNote(notes)
    override suspend fun deleteNotes(notes: Notes) =  noteDao.deleteNote(notes)
}