package com.moviecatalog.todoappcompose.featureNotes.domain.use_cases

import com.moviecatalog.todoappcompose.featureNotes.domain.model.Notes
import com.moviecatalog.todoappcompose.featureNotes.domain.repository.NoteRepository

class DeleteNoteUseCases(val repository: NoteRepository) {

    operator suspend fun invoke (note:Notes) = repository.deleteNotes(note)

}