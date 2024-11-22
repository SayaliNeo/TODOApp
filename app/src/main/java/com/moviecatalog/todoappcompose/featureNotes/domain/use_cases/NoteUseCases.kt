package com.moviecatalog.todoappcompose.featureNotes.domain.use_cases


data class NoteUseCases(
    val getNotesUseCases: GetNotesUseCases,
    val addNotesUseCase: AddNotesUseCase,
    val deleteUseCases: DeleteNoteUseCases,
    val getNoteById: GetNoteByIdUseCases
)
