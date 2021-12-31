package com.shardams.notesapp.feature_note.domain.usecase

import com.shardams.notesapp.feature_note.domain.model.InvalidNoteException
import com.shardams.notesapp.feature_note.domain.model.Note
import com.shardams.notesapp.feature_note.domain.repository.NoteRepository
import javax.inject.Inject
import kotlin.jvm.Throws

class AddNote @Inject constructor(
    private val repo: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if(note.title.isBlank()){
            throw InvalidNoteException("The title of the note cannot be empty")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("The content of the note cannot be empty")
        }
        repo.insertNote(note)
    }

}