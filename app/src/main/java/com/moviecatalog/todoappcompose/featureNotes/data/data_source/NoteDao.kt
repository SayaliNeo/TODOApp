package com.moviecatalog.todoappcompose.featureNotes.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moviecatalog.todoappcompose.featureNotes.domain.model.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("select * from notes")
    fun  getAllNotes(): Flow<List<Notes>>

    @Query("select * from notes where id=:noteid")
    suspend fun getNoteById(noteid:Int):Notes

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Notes)

    @Delete
    suspend fun deleteNote(note: Notes)

   /* @Query("delete from notes where id=:noteId")
     fun deleteNoteById(noteId:Int):Boolean*/

}