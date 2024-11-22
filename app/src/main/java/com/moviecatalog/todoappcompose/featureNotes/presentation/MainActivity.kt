package com.moviecatalog.todoappcompose.featureNotes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.moviecatalog.todoappcompose.featureNotes.presentation.add_edit_note.AddEditNoteScreen
import com.moviecatalog.todoappcompose.featureNotes.presentation.components.NoteScreen
import com.moviecatalog.todoappcompose.featureNotes.presentation.util.NavigationScreen
import com.moviecatalog.todoappcompose.ui.theme.TodoAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TodoAppComposeTheme {
              Surface(color = MaterialTheme.colorScheme.background
              ){
                  val navController = rememberNavController()
                  NavHost(
                      navController = navController,
                      startDestination = NavigationScreen.NoteScreen.route
                  ){
                      composable(route = NavigationScreen.NoteScreen.route) {
                          NoteScreen(navController = navController)
                      }

                      composable(route = NavigationScreen.AddEditNoteScreen.route +
                              "?noteId={noteId}&noteColor={noteColor}",
                          arguments = listOf(
                              navArgument(
                                  name = "noteId"
                              ) {
                                  type = NavType.IntType
                                  defaultValue = -1
                              },
                              navArgument(
                                  name = "noteColor"
                              ) {
                                  type = NavType.IntType
                                  defaultValue = -1
                              },
                          )) {
                          val color = it.arguments?.getInt("noteColor") ?: -1
                          AddEditNoteScreen(navController = navController, noteColor =color)
                      }
                  }

              }
            }
        }
    }
}


@Composable
fun Greeting() {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoAppComposeTheme {

    }
}