package com.moviecatalog.todoappcompose.featureNotes.domain.use_cases

import com.moviecatalog.todoappcompose.featureNotes.domain.model.InvalidNoteException
import com.moviecatalog.todoappcompose.featureNotes.domain.model.Notes
import com.moviecatalog.todoappcompose.featureNotes.domain.repository.NoteRepository

class AddNotesUseCase(private val repository: NoteRepository) {


    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Notes):Boolean {

        if (note.title.isBlank()) {
            return false
       }else if (note.contents.isBlank()) {
            return false
        }else{
            repository.addNotes(note)
            return true
        }

//        if (note.title.isBlank()) {
//            return InvalidNoteException("The title of Note cannot be empty")
//        }
//        if (note.contents.isBlank()) {
//            throw InvalidNoteException("The content of Note cannot be empty")
//        }

    }
}