package com.moviecatalog.todoappcompose.featureNotes.presentation.components

import com.moviecatalog.todoappcompose.featureNotes.domain.model.Notes
import com.moviecatalog.todoappcompose.featureNotes.domain.utils.NotesOrderType
import com.moviecatalog.todoappcompose.featureNotes.domain.utils.OrderType

data class NotesState (
    var  notesList:List<Notes> = emptyList(),
    val noteOrder:NotesOrderType = NotesOrderType.Time(OrderType.Descending),
    val isOrderSectionVisible:Boolean = false

)