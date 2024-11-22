package com.moviecatalog.todoappcompose.featureNotes.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecatalog.todoappcompose.featureNotes.domain.model.Notes
import com.moviecatalog.todoappcompose.featureNotes.domain.use_cases.NoteUseCases
import com.moviecatalog.todoappcompose.featureNotes.domain.utils.NotesOrderType
import com.moviecatalog.todoappcompose.featureNotes.domain.utils.OrderType
import com.moviecatalog.todoappcompose.featureNotes.presentation.components.NotesEvent
import com.moviecatalog.todoappcompose.featureNotes.presentation.components.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(private val noteUseCases: NoteUseCases):ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state : State<NotesState> = _state
    private var recentlyDeletedNote:Notes?=null
    var noteJob :Job?=null

    init {
        getNotes(noteOrder = NotesOrderType.Time(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when(event){
            is NotesEvent.OrderEvent ->{
                if(state.value.noteOrder::class == event.notesOrderType::class &&
                    state.value.noteOrder.orderType == event.notesOrderType.orderType){
                    return
                }

            getNotes(event.notesOrderType)

            }
            is NotesEvent.DeleteNote ->{
                viewModelScope.launch {
                    noteUseCases.deleteUseCases(event.notes)
                    recentlyDeletedNote = event.notes
                }

            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(isOrderSectionVisible = !state.value.isOrderSectionVisible)

            }

            is NotesEvent.RestoreLatestNote -> {
                viewModelScope.launch {
                        noteUseCases.addNotesUseCase(recentlyDeletedNote?:return@launch)
                    recentlyDeletedNote = null
                }


            }
        }


    }

    private fun getNotes(noteOrder:NotesOrderType) {
        noteJob?.cancel()
        noteJob = noteUseCases.getNotesUseCases(noteOrder).onEach {
            notes->
            _state.value = state.value.copy(notes,noteOrder)
        }.launchIn(viewModelScope)

    }

}