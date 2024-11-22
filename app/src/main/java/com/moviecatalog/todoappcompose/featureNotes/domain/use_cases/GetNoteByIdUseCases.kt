package com.moviecatalog.todoappcompose.featureNotes.domain.use_cases

import com.moviecatalog.todoappcompose.featureNotes.domain.model.Notes
import com.moviecatalog.todoappcompose.featureNotes.domain.repository.NoteRepository

class GetNoteByIdUseCases(val repository: NoteRepository) {

    suspend operator fun invoke(id:Int): Notes{
        return repository.getNotesById(id)

    }
}