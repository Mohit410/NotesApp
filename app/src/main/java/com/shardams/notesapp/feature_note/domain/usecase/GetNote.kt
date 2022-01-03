package com.shardams.notesapp.feature_note.domain.usecase

import com.shardams.notesapp.feature_note.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int) = repository.getNoteById(id)

}