package com.moviecatalog.todoappcompose.featureNotes.presentation.util

sealed class NavigationScreen(val route: String) {
    object NoteScreen: NavigationScreen("notes_screen")
    object AddEditNoteScreen: NavigationScreen("add_edit_note_screen")
}