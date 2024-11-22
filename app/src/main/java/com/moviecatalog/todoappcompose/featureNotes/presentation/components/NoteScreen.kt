package com.moviecatalog.todoappcompose.featureNotes.presentation.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsIgnoringVisibility
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.moviecatalog.todoappcompose.featureNotes.presentation.util.NavigationScreen
import com.moviecatalog.todoappcompose.featureNotes.presentation.util.TestTags
import com.moviecatalog.todoappcompose.featureNotes.presentation.viewmodel.NoteViewModel
import com.moviecatalog.todoappcompose.ui.theme.Blue

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteScreen(navController: NavController, noteViewModel: NoteViewModel = hiltViewModel()) {

    val state = noteViewModel.state.value
    val scope = rememberCoroutineScope()


    Scaffold(
        contentWindowInsets = WindowInsets.systemBarsIgnoringVisibility,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(NavigationScreen.AddEditNoteScreen.route) },
                modifier = Modifier.background(MaterialTheme.colorScheme.primary)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "AddNote")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Note",
                    style = MaterialTheme.typography.headlineMedium
                )
                IconButton(
                    onClick = {
                        noteViewModel.onEvent(NotesEvent.ToggleOrderSection)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Sort"
                    )
                }
            }

            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                visibleState = MutableTransitionState(state.isOrderSectionVisible),
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically(),
            ) {

                OrderSectionScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp).testTag(TestTags.ORDER_SECTION),
                    noteOrder = state.noteOrder,
                    onOrderChange = { noteViewModel.onEvent(NotesEvent.OrderEvent(it)) }
                )


                Spacer(modifier = Modifier.height(10.dp))

            }
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.notesList) { note ->
                    NoteItemsScreen(notes = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    NavigationScreen.AddEditNoteScreen.route +
                                            "?noteId=${note.id}&noteColor=${note.noteColor}"
                                )
                            }, onDeleteClick = {
                                    noteViewModel.onEvent(NotesEvent.DeleteNote(note))
                        })
                    Spacer(modifier = Modifier.height(10.dp))

                }

            }

        }
    }
}