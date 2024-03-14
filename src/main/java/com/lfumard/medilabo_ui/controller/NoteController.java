package com.lfumard.medilabo_ui.controller;

import com.lfumard.medilabo_ui.beans.NoteBean;
import com.lfumard.medilabo_ui.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping("/{id}")
    public String getNoteById(@PathVariable("id") String id, Model model) {
        model.addAttribute("note", noteService.getNoteById(id));
        return "note";
    }
    @PostMapping("/addNote")
    public String addNote(@ModelAttribute NoteBean note){
        noteService.addNote(note);
        return "redirect:/patient/"+note.getPatientId();
    }
    @GetMapping("/{id}/addNote")
    public String addNoteForm(@PathVariable(value = "id") Long patId, Model model){
        NoteBean note = new NoteBean();
        note.setPatientId(Long.parseLong(patId.toString()));
        model.addAttribute("note", note);
        return "addNoteForm";
    }
    @PostMapping("/deleteNote")
    public String deleteNoteById(@RequestParam(value = "noteId") String noteId, @RequestParam(value = "patId") String patId){
        noteService.deleteNoteById(noteId);
        return "redirect:/patient/" + patId;
    }
    @PostMapping("/updateNote")
    public String updateNoteById(@ModelAttribute NoteBean note){
        noteService.updateNote(note);
        return "redirect:/patient/"+note.getPatientId();
    }
}
