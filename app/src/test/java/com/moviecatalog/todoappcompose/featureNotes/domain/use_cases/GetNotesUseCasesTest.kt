package com.moviecatalog.todoappcompose.featureNotes.domain.use_cases

import com.google.common.truth.Truth.assertThat
import com.moviecatalog.todoappcompose.featureNotes.data.repository.DummyNoteRepository
import com.moviecatalog.todoappcompose.featureNotes.domain.model.Notes
import com.moviecatalog.todoappcompose.featureNotes.domain.utils.NotesOrderType
import com.moviecatalog.todoappcompose.featureNotes.domain.utils.OrderType
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetNotesUseCasesTest {

    //need repo before starting unit test
    //need to initialize notedatabase
    private lateinit var getNotes: GetNotesUseCases
    lateinit var dummyNoteRepository: DummyNoteRepository

    @Before
    fun setUp() {
        dummyNoteRepository = DummyNoteRepository()
        getNotes = GetNotesUseCases(dummyNoteRepository)


        val notesToInsert = mutableListOf<Notes>()
        ('a'..'z').forEachIndexed { index, c ->
            notesToInsert.add(
                Notes(
                    title = c.toString(),
                    contents = c.toString(),
                    noteColor = index,
                    timeStamp = index.toLong()
                )
            )

        }

        notesToInsert.shuffle()
        runBlocking { notesToInsert.forEach { dummyNoteRepository.addNotes(it) } }

    }


    @Test
    fun `Order notes by title ascending, correct order`() {
        runBlocking {
            val notes = getNotes(NotesOrderType.Title(OrderType.Ascending)).first()
                for (i in 0..notes.size - 2){
                    assertThat(notes[i].title).isLessThan(notes[i+1].title)
                }

        }
    }

    @Test
    fun `Order notes by title descending, correct order`() {
        runBlocking {
            val notes = getNotes(NotesOrderType.Title(OrderType.Descending)).first()
                for (i in 0..notes.size - 2){
                    assertThat(notes[i].title).isGreaterThan(notes[i+1].title)
                }

        }
    }

    @Test
    fun `Order of notes by timestamp ascending,correct order`(){
        runBlocking {
            val notes = getNotes(NotesOrderType.Time(OrderType.Ascending)).first()

            for(i in 0..notes.size -2){
                assertThat(notes[i].timeStamp).isLessThan(notes[i+1].timeStamp)
            }
        }

    }

    @Test
    fun `order of notes by timestamp descending,corret Order`(){
        runBlocking {
            val notes = getNotes(noteOrderType = NotesOrderType.Time(OrderType.Descending)).first()

            for (i in 0..notes.size){
                assertThat(notes[i].timeStamp).isGreaterThan(notes[i+1].timeStamp)
            }
        }
    }

}