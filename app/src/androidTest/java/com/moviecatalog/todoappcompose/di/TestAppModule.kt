package com.moviecatalog.todoappcompose.di

import android.app.Application
import androidx.room.Room
import com.moviecatalog.todoappcompose.featureNotes.data.data_source.NoteDatabase
import com.moviecatalog.todoappcompose.featureNotes.data.repository.NoteRepositoryImpl
import com.moviecatalog.todoappcompose.featureNotes.domain.repository.NoteRepository
import com.moviecatalog.todoappcompose.featureNotes.domain.use_cases.AddNotesUseCase
import com.moviecatalog.todoappcompose.featureNotes.domain.use_cases.DeleteNoteUseCases
import com.moviecatalog.todoappcompose.featureNotes.domain.use_cases.GetNoteByIdUseCases
import com.moviecatalog.todoappcompose.featureNotes.domain.use_cases.GetNotesUseCases
import com.moviecatalog.todoappcompose.featureNotes.domain.use_cases.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.inMemoryDatabaseBuilder(
            app.applicationContext,
            NoteDatabase::class.java,
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideRepository(database: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(database.roomNoteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCases = GetNotesUseCases(repository),
            addNotesUseCase = AddNotesUseCase(repository),
            deleteUseCases = DeleteNoteUseCases(repository),
            getNoteById = GetNoteByIdUseCases(repository)
        )
    }

}