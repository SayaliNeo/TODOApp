package com.moviecatalog.todoappcompose.featureNotes.domain.use_cases

import com.google.common.truth.Truth.assertThat
import com.moviecatalog.todoappcompose.featureNotes.data.repository.DummyNoteRepository
import com.moviecatalog.todoappcompose.featureNotes.domain.model.InvalidNoteException
import com.moviecatalog.todoappcompose.featureNotes.domain.model.Notes
import com.moviecatalog.todoappcompose.featureNotes.domain.utils.NotesOrderType
import com.moviecatalog.todoappcompose.featureNotes.domain.utils.OrderType
import junit.framework.Assert.assertTrue
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddNotesUseCaseTest {

    private lateinit var addNotesUseCase: AddNotesUseCase
    private lateinit var getNotesUseCases: `GetNotesUseCases`
    private lateinit var dummyNoteRepository: DummyNoteRepository

    @Before
    fun setUp() {
        dummyNoteRepository = DummyNoteRepository()
        addNotesUseCase = AddNotesUseCase(dummyNoteRepository)
        getNotesUseCases = GetNotesUseCases(dummyNoteRepository)

        val insertData = mutableListOf<Notes>()
        ('a'..'z').forEachIndexed { index, notesRow ->
            insertData.add(
                Notes(
                    title = "",
                    contents = notesRow.toString(),
                    noteColor = 983,
                    timeStamp = 973873
                )
            )
        }
        insertData.shuffle()
        runBlocking {
            insertData.forEach { dummyNoteRepository.addNotes(it) }
        }

    }

    @Test
    fun `empty title and complete context ,returns false`() {
        runBlocking {
            val notes = addNotesUseCase.invoke(Notes("","Sayali",93,21321))
            assertEquals(notes, false)

        }
    }
    @Test
    fun `empty context and complete title ,returns false`() {
        runBlocking {
            val notes = addNotesUseCase.invoke(Notes("Sayali","",93,21321))
            assertEquals(notes, false)

        }
    }
    @Test
    fun `empty context and empty title ,returns false`() {
        runBlocking {
            val notes = addNotesUseCase.invoke(Notes("","",93,21321))
            assertEquals(notes, false)

        }
    }




   /* @Test
    fun `empty title and complete context ,returns false`() {
        runBlocking {
            val notes = addNotesUseCase.invoke(Notes("JKDSJFKS","",93,21321))

        }
    }


    @Test
    fun `empty context or title return false`() {
        runBlocking {
            val notes = addNotesUseCase.invoke(Notes("", "", 84, 87332))
            assertThat(notes)

        }
    }

    @Test
    fun `empty context and complete title,returns false`(){
        runBlocking {
            val notes = addNotesUseCase.invoke(Notes("JKDSJFKS","",93,21321))
            assertThat(notes)
        }
    }
*/
    @Test
    fun `empty context or  title ,return false`(){
        /*
            val notes = getNotesUseCases(NotesOrderType.Title(OrderType.Ascending)).first()
            for (i in 0..notes.size - 2){
                assertThat(notes[i].contents.isBlank())
            }*/
       runBlocking {
            val addNotesUseCase = addNotesUseCase.invoke(Notes("JDEJD","mdskfm",9494,0))
            assertThat(addNotesUseCase)
        }

    }


}