package com.moviecatalog.todoappcompose.featureNotes.domain.utils

sealed class NotesOrderType(val orderType: OrderType) {

    class Title(orderType: OrderType):NotesOrderType(orderType)
    class Time(orderType: OrderType):NotesOrderType(orderType)
    class Color(orderType: OrderType):NotesOrderType(orderType)

    fun copy(orderType: OrderType):NotesOrderType{
        return when(this){
            is Title -> Title(orderType)
            is Color -> Color(orderType)
            is Time -> Time(orderType)
        }
    }
}