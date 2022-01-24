package com.isa.mynotes.di

import android.app.Application
import androidx.room.Room
import com.isa.mynotes.feature_note.data.data_source.NoteDatabase
import com.isa.mynotes.feature_note.domain.repository.NoteRepository
import com.isa.mynotes.feature_note.domain.repository.NoteRepositoryImpl
import com.isa.mynotes.feature_note.domain.use_case.AddNote
import com.isa.mynotes.feature_note.domain.use_case.DeleteNotes
import com.isa.mynotes.feature_note.domain.use_case.GetNotes
import com.isa.mynotes.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases{
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNotes = DeleteNotes(repository),
            addNote = AddNote(repository)
        )
    }
}