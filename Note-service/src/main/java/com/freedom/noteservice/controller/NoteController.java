package com.freedom.noteservice.controller;

import com.freedom.noteservice.entities.Note;
import com.freedom.noteservice.security.SpringUser;
import com.freedom.noteservice.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {
    @Autowired
    private NoteService noteService;

    private String email;

    public void getEmail() {
        email = ((SpringUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    /**
     * Get all notes for logged user.
     *
     * @return notes and HttpStatus.OK
     */
    @GetMapping("/")
    public ResponseEntity<List<Note>> allNotes() {
        if (email == null)
            getEmail();
        return new ResponseEntity<>(
                noteService.findAllUserNotes(email), HttpStatus.OK);
    }

    /**
     * Get note by id  for logged user.
     *
     * @param id the note id
     * @return note and HttpStatus.OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> noteById(@PathVariable final int id) {
        if (email == null)
            getEmail();
        return new ResponseEntity<>(noteService.findByEmailAndId(email, id), HttpStatus.OK);
    }

    /**
     * Create new note for logged user.
     *
     * @param note to save
     * @return the created note and HttpStatus.CREATED
     */
    @PostMapping("/")
    ResponseEntity createNote(@RequestBody Note note) {
        if (email == null)
            getEmail();
        noteService.save(note, email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Delete logged user note by id.
     *
     * @param id the note id
     * @return HttpStatus.OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteNote(@PathVariable final int id) {
        if (email == null)
            getEmail();
        boolean result = noteService.deleteNoteByUserIdAndId(email, id);
        if (!result)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Update note for logged user by id.
     *
     * @param id the note id to be updated.
     * @return HttpStatus.OK
     */
    @PutMapping("/{id}")
    public ResponseEntity updateNote(@PathVariable final int id, @RequestBody Note note) {
        if (email == null)
            getEmail();
        boolean result = noteService.updateNote(email, id, note);
        if (!result)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(HttpStatus.OK);
    }
}
