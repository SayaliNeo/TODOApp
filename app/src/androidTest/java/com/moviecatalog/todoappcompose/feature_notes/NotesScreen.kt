package com.moviecatalog.todoappcompose.feature_notes

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.moviecatalog.todoappcompose.common.AppModule
import com.moviecatalog.todoappcompose.featureNotes.domain.utils.NotesOrderType
import com.moviecatalog.todoappcompose.featureNotes.domain.utils.OrderType
import com.moviecatalog.todoappcompose.featureNotes.presentation.MainActivity
import com.moviecatalog.todoappcompose.featureNotes.presentation.components.NoteScreen
import com.moviecatalog.todoappcompose.featureNotes.presentation.ui.theme.TodoAppComposeTheme
import com.moviecatalog.todoappcompose.featureNotes.presentation.util.NavigationScreen
import com.moviecatalog.todoappcompose.featureNotes.presentation.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesScreen {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    /*@get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)*/




    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            TodoAppComposeTheme {

                NavHost(
                    navController = navController,
                    startDestination = NavigationScreen.NoteScreen.route
                ) {

                    composable(route = NavigationScreen.NoteScreen.route) {
                        NoteScreen(navController = navController)
                    }

                }
            }
        }
    }

    @Test
    fun ToggleOrderSection_isVisible(){
        val context = ApplicationProvider.getApplicationContext<MainActivity>()
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).assertDoesNotExist()
        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).isDisplayed()

    }

    @Test
    fun DisplayTitle(){
       // composeRule.NotesOrderType.Title(OrderType.Ascending)(TestTags.ORDER_SECTION).assertIsNotDisplayed()
        composeRule.onNodeWithTag(TestTags.TITLE_SECTION).isDisplayed()
    }

    @Test
    fun DisplayAccordingDate(){
       // composeRule.NotesOrderType.Title(OrderType.Ascending)(TestTags.ORDER_SECTION).assertIsNotDisplayed()
        composeRule.onNodeWithTag(TestTags.ORDER_DATE).isDisplayed()
    }
}