package com.shardams.notesapp.di

import android.app.Application
import androidx.room.Room
import com.shardams.notesapp.feature_note.data.data_source.NoteDao
import com.shardams.notesapp.feature_note.data.data_source.NoteDatabase
import com.shardams.notesapp.feature_note.data.data_source.NoteDatabase.Companion.DATABASE_NAME
import com.shardams.notesapp.feature_note.data.repository.NoteRepositoryImpl
import com.shardams.notesapp.feature_note.domain.repository.NoteRepository
import com.shardams.notesapp.feature_note.domain.usecase.*
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
    fun provideDatabase(app: Application): NoteDatabase =
        Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideNotesRepository(db: NoteDatabase): NoteRepository =
        NoteRepositoryImpl(db.noteDao)

    @Provides
    @Singleton
    fun provideNotesUseCase(repository: NoteRepository): NotesUseCases =
        NotesUseCases(
            GetNotes(repository),
            DeleteNote(repository),
            AddNote(repository),
            GetNote(repository)
        )


}