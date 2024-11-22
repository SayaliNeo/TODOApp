package com.moviecatalog.todoappcompose.featureNotes.domain.use_cases

import com.moviecatalog.todoappcompose.featureNotes.domain.model.Notes
import com.moviecatalog.todoappcompose.featureNotes.domain.repository.NoteRepository
import com.moviecatalog.todoappcompose.featureNotes.domain.utils.NotesOrderType
import com.moviecatalog.todoappcompose.featureNotes.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCases(private var repository: NoteRepository) {

    operator fun invoke(noteOrderType: NotesOrderType = NotesOrderType.Time(OrderType.Descending))
    : Flow<List<Notes>> {
        return repository.getAllNotes().map { note ->
               when(noteOrderType.orderType){
                   is OrderType.Ascending->{
                       when (noteOrderType) {
                           is NotesOrderType.Title -> note.sortedBy { it.title.lowercase() }
                           is NotesOrderType.Color -> note.sortedBy { it.noteColor }
                           is NotesOrderType.Time -> note.sortedBy { it.timeStamp }
                       }
                   }

                   is OrderType.Descending -> {
                       when(noteOrderType){
                           is NotesOrderType.Title -> note.sortedByDescending { it.title.lowercase() }
                           is NotesOrderType.Color -> note.sortedByDescending { it.noteColor }
                           is NotesOrderType.Time -> note.sortedByDescending { it.timeStamp }
                       }
                   }
        }
    }
}




}