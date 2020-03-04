package com.freedom.noteservice.repository;

import com.freedom.noteservice.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByUserId(long userId);

    Note findByUserIdAndId(long userId, long id);

    @Transactional
    void deleteNoteByUserIdAndId(long userId, long id);
}
