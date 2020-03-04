package com.freedom.noteservice.service;

import com.freedom.noteservice.entities.Note;

import java.util.List;

public interface NoteService {
    List<Note> findAllUserNotes(String email);

    List<Note> findAllByUserId(long userId);

    Note findByEmailAndId(String email, long id);

    void save(Note note, String email);

    boolean deleteNoteByUserIdAndId(String email, long id);

    boolean updateNote(String email, long id, Note note);
}
