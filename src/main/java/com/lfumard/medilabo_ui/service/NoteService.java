package com.lfumard.medilabo_ui.service;

import com.lfumard.medilabo_ui.beans.NoteBean;
import com.lfumard.medilabo_ui.proxies.NoteProxies;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService {
    
    private final NoteProxies noteProxy;

    public NoteService(NoteProxies noteProxy) {
        this.noteProxy = noteProxy;
    }

    public NoteBean getNoteById(String id, String medilaboCookie) {

        return noteProxy.getNoteById(id, "Bearer "+medilaboCookie);
    }

    public void addNote(NoteBean note, String medilaboCookie) {

        noteProxy.addNote(note, "Bearer "+medilaboCookie);
    }

    public void deleteNoteById(String noteId, String medilaboCookie) {

        noteProxy.deleteNoteById(noteId, "Bearer "+medilaboCookie);
    }

    public void updateNote(NoteBean note, String medilaboCookie) {

        noteProxy.updateNote(note, "Bearer "+medilaboCookie);
    }

    public List<NoteBean> getNoteByPatientId(Long patientId, String medilaboCookie) {

        return noteProxy.getNotesByPatId(patientId, "Bearer "+medilaboCookie);
    }
}
