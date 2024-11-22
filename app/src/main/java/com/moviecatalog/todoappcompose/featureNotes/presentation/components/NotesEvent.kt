package com.moviecatalog.todoappcompose.featureNotes.presentation.components

import com.moviecatalog.todoappcompose.featureNotes.domain.model.Notes
import com.moviecatalog.todoappcompose.featureNotes.domain.utils.NotesOrderType

sealed class NotesEvent {

    data class OrderEvent(val notesOrderType: NotesOrderType): NotesEvent()
    data class DeleteNote(val notes: Notes): NotesEvent()
    object RestoreLatestNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()

}