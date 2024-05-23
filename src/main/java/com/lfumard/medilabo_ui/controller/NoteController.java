package com.lfumard.medilabo_ui.controller;

import com.lfumard.medilabo_ui.beans.NoteBean;
import com.lfumard.medilabo_ui.beans.PatientBean;
import com.lfumard.medilabo_ui.service.NoteService;
import com.lfumard.medilabo_ui.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    private Logger logger = LoggerFactory.getLogger(NoteController.class);

    private final NoteService noteService;
    private final PatientService patientService;

    public NoteController(NoteService noteService, PatientService patientService) {
        this.noteService = noteService;
        this.patientService = patientService;
    }
    @GetMapping("/{id}")
    public String getNoteById(@PathVariable("id") String id, Model model, @CookieValue("medilabo") String medilaboCookie) {

        logger.info("New request Mapping  : getNoteById " + id);
        NoteBean noteBean = noteService.getNoteById(id, medilaboCookie);
        PatientBean patientBean = patientService.findById(noteBean.getPatientId(), medilaboCookie);

        model.addAttribute("note", noteBean);
        model.addAttribute("patientName", patientBean.getFirstName() + " " + patientBean.getLastName());
        return "note/updateNote";
    }

    @PostMapping("/addNote")
    public String addNote(@ModelAttribute("note")  NoteBean note, @CookieValue("medilabo") String medilaboCookie){

        logger.info("New request PostMapping  : addNote " + note.toString());
        noteService.addNote(note, medilaboCookie);
        return "redirect:/patient/update/"+note.getPatientId();
    }
    @GetMapping("/{id}/addNote")
    public String addNoteForm(@PathVariable(value = "id") Long patId, Model model, @CookieValue("medilabo") String medilaboCookie){

        if(patId == 0) return "redirect:/patient/list";

        logger.info("New request Mapping  : addNoteForm " + patId);
        NoteBean noteBean = new NoteBean();
        PatientBean patientBean = patientService.findById(patId, medilaboCookie);
        model.addAttribute("patientName", patientBean.getFirstName() + " " + patientBean.getLastName());
        model.addAttribute("patientId", patientBean.getId());
        model.addAttribute("note", noteBean);

        return "note/addNote";
    }
    @GetMapping("/delete/{id},{patId}")
    public String deleteNoteById(@PathVariable("id") String noteId, @PathVariable("patId") Long patientId, @CookieValue("medilabo") String medilaboCookie){

        logger.info("New request DeleteMapping  : deleteNoteById " + noteId);
        noteService.deleteNoteById(noteId, medilaboCookie);

        return "redirect:/patient/update/" + patientId.toString();
    }
    @PostMapping("/updateNote")
    public String updateNoteById(@ModelAttribute  NoteBean note, @CookieValue("medilabo") String medilaboCookie){

        logger.info("New request PostMapping updateNoteById : " + note.toString());
        noteService.updateNote(note, medilaboCookie);

        return "redirect:/patient/update/"+note.getPatientId();
    }
}
