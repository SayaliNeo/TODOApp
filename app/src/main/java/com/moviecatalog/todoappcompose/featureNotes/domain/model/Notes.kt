package com.moviecatalog.todoappcompose.featureNotes.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moviecatalog.todoappcompose.ui.theme.Green
import com.moviecatalog.todoappcompose.ui.theme.Orange
import com.moviecatalog.todoappcompose.ui.theme.Red

@Entity
data class Notes(
    var title:String,
    var contents:String,
    var noteColor:Int,
    var timeStamp:Long,
    @PrimaryKey val id:Int?=null
){
    companion object{
        var notesColorList = listOf(Red, Orange, Green)
    }
}

class InvalidNoteException(message:String):Exception(message)
