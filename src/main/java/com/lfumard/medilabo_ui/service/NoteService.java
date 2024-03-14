package com.lfumard.medilabo_ui.service;

import com.lfumard.medilabo_ui.beans.NoteBean;
import com.lfumard.medilabo_ui.proxies.NoteProxies;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    
    private final NoteProxies noteProxy;

    public NoteService(NoteProxies noteProxy) {
        this.noteProxy = noteProxy;
    }

    public NoteBean getNoteById(String id) {

        return noteProxy.getNoteById(id);
    }

    public void addNote(NoteBean note) {

        noteProxy.addNote(note);
    }

    public void deleteNoteById(String noteId) {

        noteProxy.deleteNoteById(noteId);
    }

    public void updateNote(NoteBean note) {

        noteProxy.updateNote(note);
    }
}
