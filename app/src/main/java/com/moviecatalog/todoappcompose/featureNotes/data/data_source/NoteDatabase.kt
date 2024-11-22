package com.moviecatalog.todoappcompose.featureNotes.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moviecatalog.todoappcompose.featureNotes.domain.model.Notes

@Database(entities = [Notes::class], version = 1)
abstract class NoteDatabase:RoomDatabase() {

    abstract val roomNoteDao: NoteDao

    companion object {
         const val DATABASE_NAME = "notes_db"

    }
}

    /*companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }

        private fun buildDatabase(context:Context):NoteDatabase{
            return Room.databaseBuilder(context.applicationContext,
                NoteDatabase::class.java,"note_db").fallbackToDestructiveMigration().build()
        }
    }*/
