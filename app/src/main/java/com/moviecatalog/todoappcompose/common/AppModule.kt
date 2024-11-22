package com.moviecatalog.todoappcompose.common

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
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            NoteDatabase::class.java, NoteDatabase.DATABASE_NAME
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
/*
val viewModelModule = module {
        viewModel { NoteViewModel(get()) }
}
val appModule = module {
        single<NoteRepository>{ NoteRepositoryImpl(get())}
}
val databaseModule = module{
        single { NoteDatabase.getInstance(androidContext()) }
}


val domainModule = module {
        fun provideNoteRepository(db:NoteDatabase): NoteRepository {
                return NoteRepositoryImpl(db.roomNoteDao)
        }

        single { provideNoteRepository(get()) }
        single{NoteUseCases(get(),get(),get(),get())}
        single { GetNotesUseCases(get()) }
        single { DeleteNoteUseCases(get()) }
        single { AddNotesUseCase(get()) }
}
*/
