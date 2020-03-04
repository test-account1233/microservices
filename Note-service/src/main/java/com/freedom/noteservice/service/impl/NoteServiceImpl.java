package com.freedom.noteservice.service.impl;

import com.freedom.noteservice.entities.Note;
import com.freedom.noteservice.entities.User;
import com.freedom.noteservice.repository.NoteRepository;
import com.freedom.noteservice.service.NoteService;
import com.freedom.noteservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository repository;

    @Autowired
    private UserService userService;

    @Override
    public List<Note> findAllUserNotes(String email) {
        long userId = userService.findIdByEmail(email);
        return findAllByUserId(userId);
    }

    @Override
    public List<Note> findAllByUserId(long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public Note findByEmailAndId(String email, long id) {
        long userId = userService.findIdByEmail(email);
        return repository.findByUserIdAndId(userId, id);
    }

    @Override
    public void save(Note note, String email) {
        User user = userService.findByEmail(email);
        if (note.getCreatedTime() == null)
            note.setCreatedTime(LocalDateTime.now());
        note.setUpdatedTime(LocalDateTime.now());
        note.setUser(user);
        repository.save(note);
    }

    @Override
    public boolean deleteNoteByUserIdAndId(String email, long id) {
        long userId = userService.findIdByEmail(email);
        Note noteToDelete = repository.findByUserIdAndId(userId, id);
        if (noteToDelete == null)
            return false;
        repository.deleteNoteByUserIdAndId(userId, id);
        return true;
    }

    @Override
    public boolean updateNote(String email, long id, Note note) {
        long userId = userService.findIdByEmail(email);
        Note dbNote = repository.findByUserIdAndId(userId, id);
        if (dbNote == null)
            return false;
        mergeNotes(dbNote, note);
        repository.save(dbNote);
        return true;
    }

    private void mergeNotes(Note dbNote, Note note) {
        dbNote.setTittle(note.getTittle());
        dbNote.setNote(note.getNote());
        dbNote.setUpdatedTime(LocalDateTime.now());
    }
}
